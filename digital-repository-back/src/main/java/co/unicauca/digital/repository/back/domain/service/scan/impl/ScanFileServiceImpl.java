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
import co.unicauca.digital.repository.back.domain.dto.scan.MessageType;
import co.unicauca.digital.repository.back.domain.dto.scan.UploadExcelFileResponse;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.contract.ContractStatusEnum;
import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;
import co.unicauca.digital.repository.back.domain.model.score.Score;
import co.unicauca.digital.repository.back.domain.model.scoreCriteria.ScoreCriteria;
import co.unicauca.digital.repository.back.domain.repository.contract.IContractRepository;
import co.unicauca.digital.repository.back.domain.repository.contractType.IContractTypeRepository;
import co.unicauca.digital.repository.back.domain.repository.criteria.ICriteriaRepository;
import co.unicauca.digital.repository.back.domain.repository.modalityContractType.IModalityContractTypeRepository;
import co.unicauca.digital.repository.back.domain.repository.score.IScoreRepository;
import co.unicauca.digital.repository.back.domain.repository.scoreCriteria.IScoreCriteriaRepository;
import co.unicauca.digital.repository.back.domain.repository.vendor.IVendorRepository;
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
    private final IVendorRepository vendorRepository;
    private final IModalityContractTypeRepository modalityContractTypeRepository;
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
    private boolean flagValidacionEstructura = true; 
    @Override
    public void processFile(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        final Cell cell4A = sheet.getRow(3).getCell(NUM_COLUMN_A);
        //TODO Validación Daniel
        if(cell4A.toString().equals("Código: PA-GA-5-FOR-39")){
            //System.out.println("Formato valido");

            if(sheet.getRow(7)!=null) {
                // Number and date cell
                final Cell cell8C = sheet.getRow(7).getCell(NUM_COLUMN_C);
                contractReference = excelUtils.extractReferenceNumber(cell8C.toString());
            }
            if(sheet.getRow(8)!=null) {
                // Vendor name cell
                final Cell cell9C = sheet.getRow(8).getCell(NUM_COLUMN_C);
                vendorName = cell9C.toString();
                // Identification type cell
                final Cell cell9H = sheet.getRow(8).getCell(NUM_COLUMN_H);
                vendorIdentificationType = cell9H.toString();
                // Vendor identification cell
                final Cell cell9J = sheet.getRow(8).getCell(NUM_COLUMN_J);
                vendorIdentification = excelUtils.extractIntegerValue(cell9J.toString()).toString();
            }
            if(sheet.getRow(9)!=null) {
                // Initial date cell
                final Cell cell10C = sheet.getRow(9).getCell(NUM_COLUMN_C);
                if (cell10C.getCellType() != CellType.STRING && cell10C.getLocalDateTimeCellValue() != null) {
                    initialDate = cell10C.getLocalDateTimeCellValue();
                }
                // Final date cell
                final Cell cell10I = sheet.getRow(9).getCell(NUM_COLUMN_I);
                if (cell10I.getCellType() != CellType.STRING && cell10I.getLocalDateTimeCellValue() != null) {
                    finalDate = cell10I.getLocalDateTimeCellValue();
                }
            }
            if(sheet.getRow(10)!=null) {
                // Contract subject cell
                final Cell cell11A = sheet.getRow(10).getCell(NUM_COLUMN_A);
                contractSubject = excelUtils.extractContractSubject(cell11A.toString());
            }
            if(sheet.getRow(15)!= null && sheet.getRow(16)!= null && sheet.getRow(17)!= null &&
                sheet.getRow(18)!= null && sheet.getRow(19)!= null && sheet.getRow(20)!= null &&
                sheet.getRow(21)!= null && sheet.getRow(22)!= null && sheet.getRow(23)!= null &&
                sheet.getRow(24)!= null && sheet.getRow(25)!= null) 
            {
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
            }
            if( sheet.getRow(44)!=null && sheet.getRow(45)!=null && sheet.getRow(46)!=null ) {
                final Cell cell46C = sheet.getRow(45).getCell(NUM_COLUMN_C);
                qualityCriteriaRate = excelUtils.extractIntegerValue(cell46C.toString());
                final Cell cell46G = sheet.getRow(45).getCell(NUM_COLUMN_G);
                complianceCriteriaRate = excelUtils.extractIntegerValue(cell46G.toString());
                final Cell cell46J = sheet.getRow(45).getCell(NUM_COLUMN_J);
                excecutionCriteriaRate = excelUtils.extractIntegerValue(cell46J.toString());
                // Total evaluation cells
                final Cell cell47J = sheet.getRow(46).getCell(NUM_COLUMN_J);
                totalScore = excelUtils.evaluateFormula(cell47J, evaluator);
            }
        }else{
            flagValidacionEstructura=false;
            //  System.out.println("El farmato no es correcto: "+ cell4A.toString());
            cleanData();
        }
        if (workbook != null) workbook.close();
        
    }

    @Override
    public UploadExcelFileResponse saveData() {
        boolean flag = true;
        List<String> responseMessages = new ArrayList<String>();
        MessageType messageType = null;
        Integer contractTypeId=null;
        if(flagValidacionEstructura){
            // Reference is not empty
            if (contractReference == null || contractReference.isBlank()) {
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
            if (qualityCriteriaRate == null || complianceCriteriaRate == null || excecutionCriteriaRate == null ||
                qualityCriteriaRate == 0 || complianceCriteriaRate == 0 || excecutionCriteriaRate == 0) {
                flag = false;
                responseMessages.add("Las calificaciones de los criterios no pueden estar vacías");
            }
            //Integer contractTypeId=null;
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
            if(contractSubject==null || contractSubject.isBlank()) {
                flag = false;
                responseMessages.add("El objeto del contrato no puede estar vacío");
            }
            
        }else{
            flag=false;
            responseMessages.add("El formato del archivo no es correcto, verifique la estructura del acrhivo");
        }
        //
        if (!flag) {
            messageType = MessageType.VALIDATION;
        
        } else {
            Optional<Contract> contract = this.contractRepository.findByReference(contractReference);
            if(contract.isEmpty()) {
                messageType = MessageType.CONTRACT_NOT_FOUND;
                responseMessages.add("El contrato no se encuentra en la base de datos");
            } else{
                Score objScore  = scoreRepository.findById(contract.get().getId()).get();
                boolean isEvaluationRegister = objScore.getCreateTime().equals(objScore.getUpdateTime()) ? false : true;
                if(isEvaluationRegister) {
                    messageType = MessageType.EVALUATION_ALREADY_EXISTS;
                    responseMessages.add("El contrato ya tiene una evaluación registrada");
                } else {
                    // * Save to DB
                    var qualityCriteria = this.criteriaRepository.findByNameAndCriteriaType("Calidad", criteriaType)
                            .orElseThrow();
                    var executionCriteria = this.criteriaRepository.findByNameAndCriteriaType("Ejecucion", criteriaType)
                            .orElseThrow();
                    var complianceCriteria = this.criteriaRepository.findByNameAndCriteriaType("Cumplimiento", criteriaType)
                            .orElseThrow();
                    scoreRepository.updateByContractId(totalScore, contract.get().getId(), now());
                    Optional<Score> scoreOptional = scoreRepository.findByContract(contract.get());
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
                    messageType = MessageType.EVALUATION_SAVED;
                }
            }
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
            .messageType(messageType)
            .messages(responseMessages)
            .contractInfo(contractInfo)
            .build();
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
    public List<UploadExcelFileResponse> processMassiveFile(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        
        List<UploadExcelFileResponse> massiveExcelFileResponses = new ArrayList<UploadExcelFileResponse>();
        LocalDateTime signingDate = null;
        //creacion lista encabezados estrutura carga masiva
        List<String> encabezadosArchivo = Arrays.asList("NÚMERO DE CONTRATO",
        "FECHA SUSCRIPCIÓN CONTRATO (AAAA/MM/DD)","CLASE DE CONTRATO", "OBJETO DEL CONTRATO","CONTRATISTA : TIPO IDENTIFICACIÓN"
        , "CONTRATISTA : NÚMERO DE CÉDULA o RUT","CONTRATISTA : NÚMERO DEL NIT",
         "CONTRATISTA : NOMBRE COMPLETO","SUPERVISOR : NOMBRE COMPLETO"
        ,"FECHA INICIO CONTRATO (AAAA/MM/DD)", "FECHA TERMINACIÓN CONTRATO (AAAA/MM/DD)","EVALUACIÓN");

        Row row; // cada fila del excel
        int rownum = 1; // empieza en la fila 2
        boolean isRowEmpty = false; // para saber si la fila esta vacia
        boolean isNaturalPerson = false;
        final String RUT = "2 RUT - REGISTRO ÚNICO TRIBUTARIO";
        final String CC = "3 CÉDULA DE CIUDADANÍA";
        final String CE = "4 CÉDULA DE EXTRANJERÍA";
        //Lecutra Encabezados
        final Cell cell1A = sheet.getRow(0).getCell(0);
        final Cell cell1B = sheet.getRow(0).getCell(1);
        final Cell cell1C = sheet.getRow(0).getCell(2);
        final Cell cell1D = sheet.getRow(0).getCell(3);
        final Cell cell1E = sheet.getRow(0).getCell(4);
        final Cell cell1F = sheet.getRow(0).getCell(5);
        final Cell cell1G = sheet.getRow(0).getCell(6);
        final Cell cell1H = sheet.getRow(0).getCell(7);
        final Cell cell1I = sheet.getRow(0).getCell(8);
        final Cell cell1J = sheet.getRow(0).getCell(9);
        final Cell cell1K = sheet.getRow(0).getCell(10);
        final Cell cell1L = sheet.getRow(0).getCell(11);
        List<String> lecturaEncabezadosArchivo = new ArrayList<>();
        /*if(cell1A!=null || cell1B!=null || cell1C!=null || cell1D!=null || cell1E!=null ||
        cell1F!=null || cell1G!=null && cell1H!=null && cell1I!=null && cell1J!=null && cell1K!=null
        && cell1L!=null){
            lecturaEncabezadosArchivo.addAll(Arrays.asList(cell1A.toString(),cell1B.toString(),
            cell1C.toString(),cell1D.toString(),cell1E.toString(),cell1F.toString(),cell1G.toString(),
            cell1H.toString(),cell1I.toString(),cell1J.toString(),cell1K.toString(),cell1L.toString()));
        }*/
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1A);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1B);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1C);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1D);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1E);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1F); 
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1G);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1H);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1I);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1J);
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1K);  
        agregarCeldaNoNula(lecturaEncabezadosArchivo, cell1L);     
        //System.out.println("Lista: "+lecturaEncabezadosArchivo);
        int auxContador=0;
        Integer contractTypeId;
        for(int i=0; i < encabezadosArchivo.size();i++ ){
            //System.out.println(encabezadosArchivo.get(i) + " vs " + lecturaEncabezadosArchivo.get(i));
            if(encabezadosArchivo.get(i).equals(lecturaEncabezadosArchivo.get(i))){
                //System.out.println("Estructura del archivo correcta, seleccione un archivo valido index: "+i);
                auxContador++;
                
            }
        }
        if(auxContador>=12){
            while (!isRowEmpty) { // mientras la fila no este vacia
                totalScore=0f;
                row = sheet.getRow(rownum); // se obtiene la fila
                if(row!=null) contractReference = row.getCell(NUM_COLUMN_A).toString();
                List<String> responseMessages = new ArrayList<>();
                MessageType messageType=null;
                contractTypeId=null;
                if (contractReference == null || contractReference.isBlank()) {
                    isRowEmpty = true;
                }
                // Comprobar si la celda en la coulmna L no es nula y no es NA
                if (!isRowEmpty && row!=null) {
                    if(row.getCell(NUM_COLUMN_B)!=null && row.getCell(NUM_COLUMN_B).getCellType() != CellType.STRING) {
                        signingDate = row.getCell(NUM_COLUMN_B).getLocalDateTimeCellValue();
                    }
                    contractSubject = row.getCell(NUM_COLUMN_D).toString();
                    vendorIdentificationType = row.getCell(NUM_COLUMN_E).toString();
                    vendorIdentification = "";
                    // Comprobar si es persona natural
                    if (vendorIdentificationType.equals(RUT) || vendorIdentificationType.equals(CC) || 
                        vendorIdentificationType.equals(CE)) {
                        isNaturalPerson = true;
                    }
                    // Comprobar que, si es persona natural, que la celda en la columna F no sea nula
                    var cellF = row.getCell(NUM_COLUMN_F).toString();
                    if (isNaturalPerson == true && !cellF.isBlank()) {
                        if (!(row.getCell(NUM_COLUMN_G).toString()).isBlank()) {
                            System.out.println("Error: NIT is not blank");
                        } else {
                            vendorIdentification = excelUtils.extractIntegerValue(row.getCell(NUM_COLUMN_F).toString()).toString();
                        }
                    }
                    // Comprobar que, si no es persona natural, que la celda en la columna G no sea nula
                    var cellG = row.getCell(NUM_COLUMN_G).toString();
                    if (isNaturalPerson == false && !cellG.isBlank()) {
                        if (!(row.getCell(NUM_COLUMN_F).toString()).isBlank()) {
                            System.out.println("Error: CC/RUT is not blank");
                        } else {
                            vendorIdentification = excelUtils.extractIntegerValue(row.getCell(NUM_COLUMN_G).toString()).toString();
                        }
                    }

                    var vendor = this.vendorRepository.findByIdentification(vendorIdentification);
                    if(vendor.isEmpty()) {
                        messageType = MessageType.VALIDATION;
                        responseMessages.add("El proveedor no se encuentra en la base de datos");
                    }

                    vendorName = row.getCell(NUM_COLUMN_H).toString();
                    if(row.getCell(NUM_COLUMN_J)!=null && row.getCell(NUM_COLUMN_J).getCellType() != CellType.STRING) {
                        initialDate = row.getCell(NUM_COLUMN_J).getLocalDateTimeCellValue();
                    }
                    if(row.getCell(NUM_COLUMN_K)!=null && row.getCell(NUM_COLUMN_K).getCellType() != CellType.STRING) {
                        finalDate = row.getCell(NUM_COLUMN_K).getLocalDateTimeCellValue();
                    }
                    if(row.getCell(NUM_COLUMN_L)!=null && row.getCell(NUM_COLUMN_L).getCellType() == CellType.NUMERIC) {
                        Double contractEvaluation = row.getCell(NUM_COLUMN_L).getNumericCellValue();
                        totalScore = contractEvaluation.floatValue();
                    }
                    // Comprobar si existe el tipo de contrato
                    String[] contractNumber = contractReference.split("/");
                    Optional<ContractType> contractTypeOptional = contractTypeRepository.findByExternalCode(contractNumber[0]);
                    if(contractTypeOptional.isEmpty()) {
                        // messageType = MessageType.VALIDATION;
                        responseMessages.add("El tipo de contrato no se encuentra en la base de datos");
                    } else {
                        contractTypeId = contractTypeOptional.get().getId();
                    }
                    if(row.getCell(NUM_COLUMN_L) == null || row.getCell(NUM_COLUMN_L).toString().equals("NA")) {
                        messageType = MessageType.VALIDATION;
                        responseMessages.add("Al contrato NO se le ha ingresado una evaluación en el excel.");
                    } else{
                        // Comprobar si existe el contrato
                        var contract = contractRepository.findByReference(contractReference);
                        if (contract.isEmpty()) {
                            // messageType = MessageType.CONTRACT_NOT_FOUND;
                            // responseMessages.add("El contrato no se encuentra en la Base de datos.");
                            //Crear contrato
                            if(vendor.isPresent()) {
                                var modalityContractType = modalityContractTypeRepository.findByContractModality(contractTypeId, 1);
                                var contractToSave = Contract.builder()
                                    .reference(contractReference)
                                    .signingDate(signingDate)
                                    .initialDate(initialDate)
                                    .finalDate(finalDate)
                                    .status(ContractStatusEnum.ACTIVO)
                                    .subject(contractSubject)
                                    .createTime(now())
                                    .vendor(vendor.get())
                                    .modalityContractType(modalityContractType.get())
                                    .build();
                                var contractSaved = this.contractRepository.save(contractToSave);
                                // Crear score
                                var scoreToSave = Score.builder()
                                    .contract(contractSaved)
                                    .totalScore(totalScore)
                                    .createTime(now())
                                    .build();
                                var scoreSaved = this.scoreRepository.save(scoreToSave);
                                // crear score criteria
                                var qualityCriteria = this.criteriaRepository.findByNameAndCriteriaType("Calidad", contractTypeOptional.get().getDescription())
                                    .orElseThrow();
                                var complianceCriteria = this.criteriaRepository.findByNameAndCriteriaType("Cumplimiento", contractTypeOptional.get().getDescription())
                                    .orElseThrow();
                                var executionCriteria = this.criteriaRepository.findByNameAndCriteriaType("Ejecucion", contractTypeOptional.get().getDescription())
                                    .orElseThrow();
                                var firstScoreCriteria = ScoreCriteria.builder()
                                    .score(scoreSaved)
                                    .criteria(qualityCriteria)
                                    .rate(totalScore.intValue())
                                    .createTime(now())
                                    .build();
                                var secondScoreCriteria = ScoreCriteria.builder()
                                    .score(scoreSaved)
                                    .criteria(complianceCriteria)
                                    .rate(totalScore.intValue())
                                    .createTime(now())
                                    .build();
                                var thirdScoreCriteria = ScoreCriteria.builder()
                                    .score(scoreSaved)
                                    .criteria(executionCriteria)
                                    .rate(totalScore.intValue())
                                    .createTime(now())
                                    .build();
                                // System.out.println("\nScore: "+ totalScore.intValue());
                                this.scoreCriteriaRepository.saveAll(List.of(firstScoreCriteria, secondScoreCriteria, thirdScoreCriteria));
                                vendorIdentification = contractSaved.getVendor().getIdentification();
                                initialDate = contractSaved.getInitialDate();
                                finalDate = contractSaved.getFinalDate();
                                contractSubject = contractSaved.getSubject();
                                messageType = MessageType.EVALUATION_SAVED;
                                responseMessages.add("La evaluación ha sido registrada correctamente.");
                            }
                            
                        } else {
                            Score objScore  = scoreRepository.findById(contract.get().getId()).get();
                            boolean isEvaluationRegister = objScore.getCreateTime().equals(objScore.getUpdateTime()) ? false : true;
                            if(isEvaluationRegister) {
                                messageType = MessageType.EVALUATION_ALREADY_EXISTS;
                                responseMessages.add("El contrato ya tiene una evaluación registrada.");
                            } else {
                                // Guardar en la base de datos
                                //Actualiza el score
                                scoreRepository.updateByContractId(totalScore, contract.get().getId(), now());
                                scoreRepository.flush();
                                // Guardar criterios
                                var qualityCriteria = this.criteriaRepository.findByNameAndCriteriaType("Calidad", contractTypeOptional.get().getDescription())
                                    .orElseThrow();
                                var complianceCriteria = this.criteriaRepository.findByNameAndCriteriaType("Cumplimiento", contractTypeOptional.get().getDescription())
                                    .orElseThrow();
                                var executionCriteria = this.criteriaRepository.findByNameAndCriteriaType("Ejecucion", contractTypeOptional.get().getDescription())
                                    .orElseThrow();
                                var firstScoreCriteria = ScoreCriteria.builder()
                                    .score(objScore)
                                    .criteria(qualityCriteria)
                                    .rate(totalScore.intValue())
                                    .createTime(now())
                                    .build();
                                var secondScoreCriteria = ScoreCriteria.builder()
                                    .score(objScore)
                                    .criteria(complianceCriteria)
                                    .rate(totalScore.intValue())
                                    .createTime(now())
                                    .build();
                                var thirdScoreCriteria = ScoreCriteria.builder()
                                    .score(objScore)
                                    .criteria(executionCriteria)
                                    .rate(totalScore.intValue())
                                    .createTime(now())
                                    .build();
                                // System.out.println("\nScore: "+ totalScore.intValue());
                                this.scoreCriteriaRepository.saveAll(List.of(firstScoreCriteria, secondScoreCriteria, thirdScoreCriteria));
                                vendorIdentification = contract.get().getVendor().getIdentification();
                                initialDate = contract.get().getInitialDate();
                                finalDate = contract.get().getFinalDate();
                                contractSubject = contract.get().getSubject();
                                messageType = MessageType.EVALUATION_SAVED;
                                responseMessages.add("La evaluación ha sido registrada correctamente.");
                            }
                        }
                    }
                    var contractInfo = ContractEvaluationInfo.builder()
                        .vendorName(vendorName)
                        .identification(vendorIdentification)
                        .initialDate(initialDate)
                        .finalDate(finalDate)
                        .subject(contractSubject)
                        .contractTypeId(contractTypeId)
                        .qualityRate(totalScore.intValue())
                        .complianceRate(totalScore.intValue())
                        .excecutionRate(totalScore.intValue())
                        .totalScore(totalScore)
                        .build();
                    var uploadExcelFileResponse = UploadExcelFileResponse.builder()
                        .reference(contractReference)
                        .messageType(messageType)
                        .messages(responseMessages)
                        .contractInfo(contractInfo)
                        .build();
                    massiveExcelFileResponses.add(uploadExcelFileResponse);
                }
                // System.out.println("Row: "+rownum);
                rownum++;
            }
        }else{ 
            List<String> responseMessagesError = new ArrayList<>();
            MessageType messageTypeM = MessageType.VALIDATION;
            responseMessagesError.add("Error en la lectura del archivo, por favor revise que el formato este bien estructurado y dilegenciado ");
            var uploadExcelFileResponse = UploadExcelFileResponse.builder()
                .reference(null)
                .messageType(messageTypeM)
                .messages(responseMessagesError)
                .contractInfo(null)
                .build();
            massiveExcelFileResponses.add(uploadExcelFileResponse);  
        }
        if (workbook != null) workbook.close();
        cleanData();
        return massiveExcelFileResponses;
    }
    private static void agregarCeldaNoNula(List<String> lista, Cell celda) {
        // Verificar que la celda no sea nula antes de agregarla a la lista
        if (celda != null) {
            lista.add(celda.toString());
        } else {
            lista.add("ERROR");   
        }
    }
}