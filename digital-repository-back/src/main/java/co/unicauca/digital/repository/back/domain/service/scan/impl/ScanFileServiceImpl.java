package co.unicauca.digital.repository.back.domain.service.scan.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.unicauca.digital.repository.back.domain.dto.scan.ContractEvaluationInfo;
import co.unicauca.digital.repository.back.domain.dto.scan.UploadExcelFileResponse;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;
import co.unicauca.digital.repository.back.domain.model.score.Score;
import co.unicauca.digital.repository.back.domain.model.scoreCriteria.ScoreCriteria;
import co.unicauca.digital.repository.back.domain.repository.contract.IContractRepository;
import co.unicauca.digital.repository.back.domain.repository.contractType.IContractTypeRepository;
import co.unicauca.digital.repository.back.domain.repository.criteria.ICriteriaRepository;
import co.unicauca.digital.repository.back.domain.repository.score.IScoreRepository;
import co.unicauca.digital.repository.back.domain.repository.scoreCriteria.IScoreCriteriaRepository;
import co.unicauca.digital.repository.back.domain.service.scan.IScanFileService;
import co.unicauca.digital.repository.back.domain.utilities.ExcelUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScanFileServiceImpl implements IScanFileService {
    /* Dependencies */
    private final ExcelUtils excelUtils;
    private final IScoreRepository scoreRepository;
    private final IScoreCriteriaRepository scoreCriteriaRepository;
    private final ICriteriaRepository criteriaRepository;
    private final IContractRepository contractRepository;
    private final IContractTypeRepository contractTypeRepository;
    /* Excel column numbers */
    private final int NUM_COLUMN_A = 0;
    private final int NUM_COLUMN_B = 1;
    private final int NUM_COLUMN_C = 2;
    private final int NUM_COLUMN_D = 3;
    private final int NUM_COLUMN_E = 4;
    private final int NUM_COLUMN_F = 5;
    private final int NUM_COLUMN_G = 6;
    private final int NUM_COLUMN_H = 7;
    private final int NUM_COLUMN_I = 8;
    private final int NUM_COLUMN_J = 9;
    private final int NUM_COLUMN_K = 10;
    private final int NUM_COLUMN_L = 11;
    /* Contract information */
    private String contractReference;
    private String vendorName;
    private String vendorIdentificationType;
    private String vendorIdentification;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private String contractSubject;
    /* Vendor type */
    private List<String> vendorTypes;
    private String criteriaType;
    /* Score */
    private Integer qualityCriteriaRate;
    private Integer complianceCriteriaRate;
    private Integer excecutionCriteriaRate;
    private Float totalScore;

    @Override
    public void processFile(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        System.out.println("===================== Data Evaluacion Proveedores V2 =============");
        System.out.println("===== Contract information =====");
        
        if(sheet.getRow(7)!=null) {
            //* Number and date cell
            final Cell cell8C = sheet.getRow(7).getCell(NUM_COLUMN_C);
            contractReference = excelUtils.extractReferenceNumber(cell8C.toString());
            System.out.println("Número de referencia: " + contractReference);
        }

        if(sheet.getRow(8)!=null) {
            // Vendor name cell
            final Cell cell9C = sheet.getRow(8).getCell(NUM_COLUMN_C);
            vendorName = cell9C.toString();
            System.out.println("Vendor name:" + vendorName);
            // Identification type cell
            final Cell cell9H = sheet.getRow(8).getCell(NUM_COLUMN_H);
            vendorIdentificationType = cell9H.toString();
            System.out.println("Identification type:" + vendorIdentificationType);
            //* Vendor cc,nit O identification cell
            final Cell cell9J = sheet.getRow(8).getCell(NUM_COLUMN_J);
            vendorIdentification = excelUtils.extractIntegerValue(cell9J.toString()).toString();
            System.out.println("Vendor Identification: " + vendorIdentification);
        }
        
        if(sheet.getRow(9)!=null) {
            // Initial date cell
            final Cell cell10C = sheet.getRow(9).getCell(NUM_COLUMN_C);
            if (cell10C.getCellType() != CellType.STRING && cell10C.getLocalDateTimeCellValue() != null) {
                initialDate = cell10C.getLocalDateTimeCellValue();
                System.out.println("Initial date: " + initialDate.toString());
            }
            // Final date cell
            final Cell cell10I = sheet.getRow(9).getCell(NUM_COLUMN_I);
            if (cell10I.getCellType() != CellType.STRING && cell10I.getLocalDateTimeCellValue() != null) {
                finalDate = cell10I.getLocalDateTimeCellValue();
                System.out.println("Final Date: " + finalDate.toString());
            }
        }
        
        if(sheet.getRow(10)!=null) {
            // Contract subject cell
            final Cell cell11A = sheet.getRow(10).getCell(NUM_COLUMN_A);
            contractSubject = excelUtils.extractContractSubject(cell11A.toString());
            System.out.println("Contract Subject: " + contractSubject);
        }
        
        if(sheet.getRow(15)!= null && sheet.getRow(16)!= null && sheet.getRow(17)!= null &&
            sheet.getRow(18)!= null && sheet.getRow(19)!= null && sheet.getRow(20)!= null &&
            sheet.getRow(21)!= null && sheet.getRow(22)!= null && sheet.getRow(23)!= null &&
            sheet.getRow(24)!= null && sheet.getRow(25)!= null) 
        {
            System.out.println("==== Vendor type ====");
            // Vendor type cells
            // Goods
            final Cell cell16J = sheet.getRow(15).getCell(NUM_COLUMN_J);
            final Cell cell17J = sheet.getRow(16).getCell(NUM_COLUMN_J);
            final Cell cell18J = sheet.getRow(17).getCell(NUM_COLUMN_J);
            // Services
            final Cell cell19J = sheet.getRow(18).getCell(NUM_COLUMN_J);
            final Cell cell20J = sheet.getRow(19).getCell(NUM_COLUMN_J);
            final Cell cell21J = sheet.getRow(20).getCell(NUM_COLUMN_J);
            final Cell cell22J = sheet.getRow(21).getCell(NUM_COLUMN_J);
            final Cell cell23J = sheet.getRow(22).getCell(NUM_COLUMN_J);
            final Cell cell24J = sheet.getRow(23).getCell(NUM_COLUMN_J);
            final Cell cell25J = sheet.getRow(24).getCell(NUM_COLUMN_J);
            // Works
            final Cell cell26J = sheet.getRow(25).getCell(NUM_COLUMN_J);
            
            // Determine the type of vendor to evaluate
            vendorTypes = Arrays.asList(cell16J.toString(), cell17J.toString(), cell18J.toString(),
                    cell19J.toString(), cell20J.toString(), cell21J.toString(), cell22J.toString(),
                    cell23J.toString(), cell24J.toString(), cell25J.toString(), cell26J.toString());
            criteriaType = excelUtils.determineVendorType(vendorTypes);
            System.out.println("El criteria type es: " + criteriaType);
        }
        
        System.out.println("==== Score ====");
        if( sheet.getRow(44)!=null && sheet.getRow(45)!=null && sheet.getRow(46)!=null ) {
            // First Criteria (Quality) scoring cells
            final Cell cell45A = sheet.getRow(44).getCell(NUM_COLUMN_A);
            String firstCriteriaType = cell45A.toString();
            final Cell cell46C = sheet.getRow(45).getCell(NUM_COLUMN_C);
            qualityCriteriaRate = excelUtils.extractIntegerValue(cell46C.toString());
            // Second Criteria (Cumplimiento) scoring cells
            final Cell cell45E = sheet.getRow(44).getCell(NUM_COLUMN_E);
            String secondCriteria = cell45E.toString();
            final Cell cell46G = sheet.getRow(45).getCell(NUM_COLUMN_G);
            complianceCriteriaRate = excelUtils.extractIntegerValue(cell46G.toString());
            // Third Criteria (execute) scoring cells
            final Cell cell45H = sheet.getRow(44).getCell(NUM_COLUMN_H);
            String thirdCriteria = cell45H.toString();
            final Cell cell46J = sheet.getRow(45).getCell(NUM_COLUMN_J);
            excecutionCriteriaRate = excelUtils.extractIntegerValue(cell46J.toString());
            System.out.println(firstCriteriaType + ": " + qualityCriteriaRate + "\n" +
                    secondCriteria + ": " + complianceCriteriaRate + "\n" +
                    thirdCriteria + ": " + excecutionCriteriaRate + "\n");
            
            // Total evaluation cells
            final Cell cell47J = sheet.getRow(46).getCell(NUM_COLUMN_J);
            totalScore = excelUtils.evaluateFormula(cell47J, evaluator);
            System.out.println("Total score: " + totalScore + "\n");
        }
        
        if (workbook != null) workbook.close();
    }

    @Override
    public UploadExcelFileResponse saveData() {
        boolean flag = true;
        List<String> responseMessages = new ArrayList<String>();
        //String baseMessage = "El contrato con máscara: " + contractReference + " ";
        // Reference is not empty
        if (contractReference == null) {
            flag = false;
            responseMessages.add("La máscara de contrato no puede estar vacía");
        }
        
        if (vendorIdentification == null || vendorIdentification.equals("0")) {
            // ID vendor is empty
            flag = false;
            responseMessages.add("El número de identificación del proveedor no puede estar vacío");
        } else if (vendorIdentification.equals("-1")) {
            // ID vendor must be a number
            flag = false;
            responseMessages.add("El número de identificación del proveedor no puede contener letras o caracteres especiales");
        }

        // Calification rate is not empty
        if (qualityCriteriaRate == null || complianceCriteriaRate == null || excecutionCriteriaRate == null) {
            flag = false;
            responseMessages.add("Las calificaciones de los criterios no pueden estar vacías");
        }

        Integer contractTypeId=null;
        // Reference with contract type
        if(vendorTypes!=null && !contractReference.equals("")) {
            HashMap<String, String> contractTypesMap = new HashMap<>();
            contractTypesMap.put("5.5-31.3", vendorTypes.get(0)); //X
            contractTypesMap.put("5.5-31.6", vendorTypes.get(1));
            contractTypesMap.put("5.5-31.X", vendorTypes.get(2));
            contractTypesMap.put("5.5-31.5", vendorTypes.get(3)); //X
            contractTypesMap.put("5.5-31.9", vendorTypes.get(4));
            // contractTypesMap.put("5.5-31.6", vendorTypes.get(5));
            contractTypesMap.put("5.5-31.1", vendorTypes.get(6));
            contractTypesMap.put("5.5-31.X", vendorTypes.get(7));
            contractTypesMap.put("5.5-31.X", vendorTypes.get(8));
            contractTypesMap.put("5.5-31.7", vendorTypes.get(9));
            contractTypesMap.put("5.5-31.4", vendorTypes.get(10));
            
            String[] contractNumber = contractReference.split("/");
            Optional<ContractType> contractTypeOptional = contractTypeRepository.findByExternalCode(contractNumber[0]);
            
            if(contractTypeOptional.isEmpty()) {
                flag = false;
                responseMessages.add("El tipo de contrato no se encuentra en la base de datos");
            } else {
                contractTypeId = contractTypeOptional.get().getId();
                if(!contractTypesMap.get(contractNumber[0]).equals("x")) {
                    flag = false;
                    responseMessages.add("El tipo de proveedor no coincide con la máscara especificada");
                }
            }
        }

        if(initialDate==null) {
            flag = false;
            responseMessages.add("La fecha de inicio no puede estar vacía");
        }

        if(finalDate==null) {
            flag = false;
            responseMessages.add("La fecha de terminación puede estar vacía");
        }

        if(contractSubject==null) {
            flag = false;
            responseMessages.add("El objeto del contrato no puede estar vacío");
        }

        var contractInfo = ContractEvaluationInfo.builder()
            .vendorName(vendorName)
            .identification(vendorIdentification)
            .initialDate(initialDate)
            .finalDate(finalDate)
            .subject(contractSubject)
            .contractTypeId(contractTypeId)
            .qualityRate(qualityCriteriaRate)
            .complianceRate(complianceCriteriaRate)
            .excecutionRate(excecutionCriteriaRate)
            .totalScore(totalScore)
            .build();
        var uploadExcelFileResponse = UploadExcelFileResponse.builder()
            .reference(contractReference)
            .messages(responseMessages)
            .contractInfo(contractInfo)
            .build();
        if(flag) {
            // * Save to DB
            Optional<Contract> contract = this.contractRepository.findByReference(contractReference);

            if(contract.isEmpty()) {
                responseMessages.add("El contrato no se encuentra en la base de datos");
                cleanData();
                return uploadExcelFileResponse;
            }

            System.out.println("Contract reference: " + contract.get().getReference());

            var qualityCriteria = this.criteriaRepository.findByNameAndCriteriaType("Calidad", criteriaType)
                    .orElseThrow();
            var executionCriteria = this.criteriaRepository.findByNameAndCriteriaType("Ejecucion", criteriaType)
                    .orElseThrow();
            var complianceCriteria = this.criteriaRepository.findByNameAndCriteriaType("Cumplimiento", criteriaType)
                    .orElseThrow();

            Optional<Contract> contractOptional = contractRepository.findByReference(contractReference);
            scoreRepository.updateByContractId(totalScore, contractOptional.get().getId());

            Optional<Score> scoreOptional = scoreRepository.findByContract(contractOptional.get());

            ScoreCriteria firstScoreCriteria = ScoreCriteria.builder()
                    .score(scoreOptional.get())
                    .criteria(qualityCriteria)
                    .rate(qualityCriteriaRate)
                    .createTime(now())
                    .build();
            ScoreCriteria secondScoreCriteria = ScoreCriteria.builder()
                    .score(scoreOptional.get())
                    .criteria(complianceCriteria)
                    .rate(complianceCriteriaRate)
                    .createTime(now())
                    .build();
            ScoreCriteria thirdScoreCriteria = ScoreCriteria.builder()
                    .score(scoreOptional.get())
                    .criteria(executionCriteria)
                    .rate(excecutionCriteriaRate)
                    .createTime(now())
                    .build();
            this.scoreCriteriaRepository.saveAll(List.of(firstScoreCriteria, secondScoreCriteria, thirdScoreCriteria));
            responseMessages.add("La evaluación del contrato ha sido registrada correctamente");
        }
        
        cleanData();
        return uploadExcelFileResponse;
    }

    private void cleanData() {
        /* Contract information */
        contractReference = null;
        vendorName = null;
        vendorIdentificationType = null;
        vendorIdentification = null;
        initialDate = null;
        finalDate = null;
        contractSubject = null;
        /* Vendor type */
        vendorTypes = null;
        criteriaType = null;
        /* Score */
        qualityCriteriaRate = null;
        complianceCriteriaRate = null;
        excecutionCriteriaRate = null;
        totalScore = null;
    }

    @Override
    public List<String> processMassiveFile(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        
        ArrayList<String> listaMensajes = new ArrayList<String>();
        Row row; // cada fila del excel
        int rownum = 1; // empieza en la fila 2
        boolean isRowEmpty = false; // para saber si la fila esta vacia
        boolean isNaturalPerson = false;
        final String RUT = "2 RUT - REGISTRO ÚNICO TRIBUTARIO";
        final String CC = "3 CÉDULA DE CIUDADANÍA";
        final String CE = "4 CÉDULA DE EXTRANJERÍA";

        System.out.println("\n============= Data Massive File ==============\n");
        while (!isRowEmpty) { // mientras la fila no este vacia
            row = sheet.getRow(rownum); // se obtiene la fila
            contractReference = row.getCell(NUM_COLUMN_A).toString();
            if (contractReference.isBlank()) {
                isRowEmpty = true;
            }
            // Comprobar si la celda en la coulmna L no es nula y no es NA
            if (!isRowEmpty && row.getCell(NUM_COLUMN_L) != null
                    && !row.getCell(NUM_COLUMN_L).toString().equals("NA")) {
                // Comprobar si existe el contrato
                var contract = contractRepository.findByReference(contractReference);
                if (contract.isPresent()) {
                    System.out.println("Printing row " + (rownum + 1) + "\n");

                    System.out.println("Reference: " + contractReference);

                    LocalDateTime suscriptionDate = row.getCell(NUM_COLUMN_B).getLocalDateTimeCellValue();
                    if (suscriptionDate != null)
                        System.out.println("Susciption date: " + suscriptionDate);

                    String contractType = row.getCell(NUM_COLUMN_C).toString();
                    if (!contractType.isBlank())
                        System.out.println("Contract type: " + contractType);

                    String contractSubject = row.getCell(NUM_COLUMN_D).toString();
                    if (!contractSubject.isBlank())
                        System.out.println("Contract subject: " + contractSubject);

                    String identificationType = row.getCell(NUM_COLUMN_E).toString();
                    if (!identificationType.isBlank())
                        System.out.println("Identification type: " + identificationType);

                    String identificationNumber = "";
                    // Comprobar si es persona natural
                    if (identificationType.equals(RUT) || identificationType.equals(CC)
                            || identificationType.equals(CE)) {
                        isNaturalPerson = true;
                    }
                    // Comprobar que, si es persona natural,
                    // que la celda en la columna F no sea nula
                    var cellF = row.getCell(NUM_COLUMN_F).toString();
                    if (isNaturalPerson == true && !cellF.isBlank()) {
                        if (!(row.getCell(NUM_COLUMN_G).toString()).isBlank()) {
                            System.out.println("Error: NIT is not blank");
                        } else {
                            identificationNumber = row.getCell(NUM_COLUMN_F).toString();
                        }
                    }
                    // Comprobar que, si no es persona natural,
                    // que la celda en la columna G no sea nula
                    var cellG = row.getCell(NUM_COLUMN_G).toString();
                    if (isNaturalPerson == false && !cellG.isBlank()) {
                        if (!(row.getCell(NUM_COLUMN_F).toString()).isBlank()) {
                            System.out.println("Error: CC/RUT is not blank");
                        } else {
                            identificationNumber = row.getCell(NUM_COLUMN_G).toString();
                        }
                    }
                    if (!identificationNumber.isBlank())
                        System.out.println("Identification number: " + identificationNumber);

                    String vendorName = row.getCell(NUM_COLUMN_H).toString();
                    if (!vendorName.isBlank())
                        System.out.println("Vendor name: " + vendorName);

                    String supervisorName = row.getCell(NUM_COLUMN_I).toString();
                    if (!supervisorName.isBlank())
                        System.out.println("Supervisor name: " + supervisorName);

                    LocalDateTime initialDate = row.getCell(NUM_COLUMN_J).getLocalDateTimeCellValue();
                    if (initialDate != null)
                        System.out.println("Initial date: " + initialDate);

                    LocalDateTime finalDate = row.getCell(NUM_COLUMN_K).getLocalDateTimeCellValue();
                    if (finalDate != null)
                        System.out.println("Final date: " + finalDate);

                    Double contractEvaluation = row.getCell(NUM_COLUMN_L).getNumericCellValue();
                    totalScore = contractEvaluation.floatValue();
                    System.out.println("Contract evaluation: " + totalScore + "\n");

                    // Guardar en la base de datos
                    Optional<Contract> contractOptional = contractRepository.findByReference(contractReference);
                    scoreRepository.updateByContractId(totalScore, contractOptional.get().getId());
                    listaMensajes.add("El contrato con mascara: "+ contractReference + " ha sido guardado correctamente.");
                } else {
                    listaMensajes.add("El contrato con mascara: "+ contractReference + " no se encuentra en la Base de datos.");
                }
            } else {
                listaMensajes.add("El contrato con mascara: "+ contractReference + " no se le ha ingresado una evaluación.");
            }
            rownum++;
        }
        System.out.println("================= End =====================");
        if (workbook != null)
            workbook.close();
        cleanData();
        return listaMensajes;
    }

}
