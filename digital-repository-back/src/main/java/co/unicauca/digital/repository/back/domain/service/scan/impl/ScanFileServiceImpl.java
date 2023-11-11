package co.unicauca.digital.repository.back.domain.service.scan.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.unicauca.digital.repository.back.domain.model.score.Score;
import co.unicauca.digital.repository.back.domain.model.scorecriteria.ScoreCriteria;
import co.unicauca.digital.repository.back.domain.repository.contract.IContractRepository;
import co.unicauca.digital.repository.back.domain.repository.criteria.ICriteriaRepository;
import co.unicauca.digital.repository.back.domain.repository.score.IScoreRepository;
import co.unicauca.digital.repository.back.domain.repository.scorecriteria.IScoreCriteriaRepository;
import co.unicauca.digital.repository.back.domain.service.scan.IScanFileService;
import co.unicauca.digital.repository.back.domain.utilities.ExcelUtils;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScanFileServiceImpl implements IScanFileService {

    private final ExcelUtils excelUtils;
    private final IScoreRepository scoreRepository;
    private final IScoreCriteriaRepository scoreCriteriaRepository;
    private final ICriteriaRepository criteriaRepository;
    private final IContractRepository contractRepository;
    /* Contract information */
    private String contractReference;
    private String vendorName;
    private String vendorIdentificationType;
    private String vendorIdentification;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private String contractSubject;
    /* Vendor type */
    List<String> vendorTypes;
    private String criteriaType;
    /* Score */
    private Integer qualityCriteriaRate;
    private Integer complianceCriteriaRate;
    private Integer excecutionCriteriaRate;
    private Float totalScore;

    @Override
    public void processFile(MultipartFile file) throws IOException, ParseException {

        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        System.out.println("===================== Data Evaluacion Proveedores V2 =============");

        // * Contract reference cell
        /*
         * final Cell cell4A = sheet.getRow(3).getCell(0);
         * String code = excelUtils.extractCode(cell4A.toString());
         * System.out.println("Code: " + code);
         */

        // * Evaluation version cell
        /*
         * final Cell cell4D = sheet.getRow(3).getCell(3);
         * Integer version = excelUtils.extractVersion(cell4D.toString());
         * System.out.println("Version: " + version);
         */

        // * Evaluation update date cell
        /*
         * final Cell cell4H = sheet.getRow(3).getCell(7);
         * LocalDateTime evaluationUpdateDate =
         * excelUtils.extractEvaluationUpdateDate(cell4H.toString());
         * System.out.println("Evaluation update date: " + evaluationUpdateDate);
         */

        // * Evaluation date cell
        /*
         * final Cell cell6C = sheet.getRow(5).getCell(2);
         * if (cell6C != null) {
         * LocalDateTime evaluationDate =
         * excelUtils.extractEvaluationDate(cell6C.toString());
         * System.out.println("Evaluation date: " + evaluationDate);
         * }
         */

        System.out.println("===== Contract information =====");

        // * Number and date cell
        final Cell cell8C = sheet.getRow(7).getCell(2);
        contractReference = excelUtils.extractReferenceNumber(cell8C.toString());
        System.out.println("Número de referencia: " + contractReference);

        // * Vendor name cell
        final Cell cell9C = sheet.getRow(8).getCell(2);
        vendorName = cell9C.toString();
        System.out.println("Vendor name:" + vendorName);

        // * Identification type cell
        final Cell cell9H = sheet.getRow(8).getCell(7);
        vendorIdentificationType = cell9H.toString();
        System.out.println("Identification type:" + vendorIdentificationType);

        // * Vendor cc,nit O identification cell
        final Cell cell9J = sheet.getRow(8).getCell(9);
        vendorIdentification = excelUtils.extractIntegerValue(cell9J.toString()).toString();
        System.out.println("Vendor Identification: " + vendorIdentification);

        // * Initial date cell
        final Cell cell10C = sheet.getRow(9).getCell(2);
        if (cell10C.getLocalDateTimeCellValue() != null) {
            initialDate = cell10C.getLocalDateTimeCellValue();
            System.out.println("Initial date: " + initialDate.toString());
        }

        // * Final date cell
        final Cell cell10I = sheet.getRow(9).getCell(8);
        if (cell10I.getLocalDateTimeCellValue() != null) {
            finalDate = cell10I.getLocalDateTimeCellValue();
            System.out.println("Final Date: " + finalDate.toString());
        }

        // * Contract subject cell
        final Cell cell11A = sheet.getRow(10).getCell(0);
        contractSubject = excelUtils.extractContractSubject(cell11A.toString());
        System.out.println("Contract Subject: " + contractSubject);

        System.out.println("==== Vendor type ====");
        // * Vendor type cells
        // Goods
        final Cell cell16J = sheet.getRow(15).getCell(9);
        // System.out.println("1. Por contrato de Compraventa: " + cell16J.toString());
        final Cell cell17J = sheet.getRow(16).getCell(9);
        // System.out.println("2. Por contrato de Suministro: " + cell17J.toString());
        final Cell cell18J = sheet.getRow(17).getCell(9);
        // System.out.println("3. Por contrato de orden de compra: " +
        // cell18J.toString());
        // Services
        final Cell cell19J = sheet.getRow(18).getCell(9);
        // System.out.println("1. Por contrato de Prestación de servicios: " +
        // cell19J.toString());
        final Cell cell20J = sheet.getRow(19).getCell(9);
        // System.out.println("2. Por contrato de Consultoría: " + cell20J.toString());
        final Cell cell21J = sheet.getRow(20).getCell(9);
        // System.out.println("3. Por contrato de Suministro: " + cell21J.toString());
        final Cell cell22J = sheet.getRow(21).getCell(9);
        // System.out.println("4. Por contrato de arrendamiento: " +
        // cell22J.toString());
        final Cell cell23J = sheet.getRow(22).getCell(9);
        // System.out.println("5. Por contrato de pasantia: " + cell23J.toString());
        final Cell cell24J = sheet.getRow(23).getCell(9);
        // System.out.println("6. Por contrato de Judicatura: " + cell24J.toString());
        final Cell cell25J = sheet.getRow(24).getCell(9);
        // System.out.println("7. Por contrato de Aprendizaje: " + cell25J.toString());
        // Works
        final Cell cell26J = sheet.getRow(25).getCell(9);
        // System.out.println("1. Por contrato de Obra: " + cell26J.toString());
        // * Determine the type of vendor to evaluate
        vendorTypes = Arrays.asList(cell16J.toString(), cell17J.toString(), cell18J.toString(),
                cell19J.toString(), cell20J.toString(), cell21J.toString(), cell22J.toString(),
                cell23J.toString(), cell24J.toString(), cell25J.toString(), cell26J.toString());
        criteriaType = excelUtils.determineVendorType(vendorTypes);
        System.out.println("El criteria type es: " + criteriaType);

        System.out.println("==== Score ====");
        // * First Criteria (Quality) scoring cells
        final Cell cell45A = sheet.getRow(44).getCell(0);
        String firstCriteriaType = cell45A.toString();
        final Cell cell46C = sheet.getRow(45).getCell(2);
        qualityCriteriaRate = excelUtils.extractIntegerValue(cell46C.toString());

        // * Second Criteria (Cumplimiento) scoring cells
        final Cell cell45E = sheet.getRow(44).getCell(4);
        String secondCriteria = cell45E.toString();
        final Cell cell46G = sheet.getRow(45).getCell(6);
        complianceCriteriaRate = excelUtils.extractIntegerValue(cell46G.toString());

        // * Third Criteria (execute) scoring cells
        final Cell cell45H = sheet.getRow(44).getCell(7);
        String thirdCriteria = cell45H.toString();
        final Cell cell46J = sheet.getRow(45).getCell(9);
        excecutionCriteriaRate = excelUtils.extractIntegerValue(cell46J.toString());

        System.out.println(firstCriteriaType + ": " + qualityCriteriaRate + "\n" +
                secondCriteria + ": " + complianceCriteriaRate + "\n" +
                thirdCriteria + ": " + excecutionCriteriaRate + "\n");

        // * Total evaluation cells
        final Cell cell47J = sheet.getRow(46).getCell(9);
        totalScore = excelUtils.evaluateFormula(cell47J, evaluator);
        System.out.println("Total score: " + totalScore + "\n");

        // * Supervisor data cells
        /*
         * final Cell cell49B = sheet.getRow(48).getCell(1);
         * String nameSupervisor = cell49B.toString();
         * final Cell cell49F = sheet.getRow(48).getCell(5);
         * String signatureSuoervisor = cell49F.toString();
         * final Cell cell49H = sheet.getRow(48).getCell(7);
         * String signatureContratist = cell49H.toString();
         * System.out.println("Supervisor fullname: " + nameSupervisor+"\n"
         * + "signature supervisor"+ signatureSuoervisor+"\n"
         * +"Signature Contratist"+ signatureContratist
         * );
         */

        // * buy orders cell
        /*
         * final Cell cell51C = sheet.getRow(50).getCell(2);
         * String nameProfessionalInventary = cell51C.toString();
         * final Cell cell51H = sheet.getRow(50).getCell(7);
         * String signatureProfessionalInventary = cell51H.toString();
         * System.out.println("Ordenes de compra(Solo si aplica)" + "\n" +
         * " Nombre profesional especializado" + nameProfessionalInventary + "\n" +
         * "signature Personal especializado inventario: " +
         * signatureProfessionalInventary
         * );
         */

        if (workbook != null)
            workbook.close();

        // * Save data
        // saveData();
    }

    @Override
    public void saveData() {
        // * Save to DB
        var contract = this.contractRepository.findByReference(contractReference)
                .orElseThrow(() -> new BusinessRuleException("contract.request.not.found"));
        System.out.println("Contract reference: " + contract.getReference());

        var qualityCriteria = this.criteriaRepository.findByNameAndCriteriaType("Calidad", criteriaType)
                .orElseThrow();
        var executionCriteria = this.criteriaRepository.findByNameAndCriteriaType("Ejecucion", criteriaType)
                .orElseThrow();
        var complianceCriteria = this.criteriaRepository.findByNameAndCriteriaType("Cumplimiento", criteriaType)
                .orElseThrow();

        Score score = Score.builder()
                .totalScore(totalScore)
                .contract(contract)
                .createTime(now())
                .build();
        this.scoreRepository.save(score);

        ScoreCriteria firstScoreCriteria = ScoreCriteria.builder()
                .score(score)
                .criteria(qualityCriteria)
                .rate(qualityCriteriaRate)
                .createTime(now())
                .build();
        ScoreCriteria secondScoreCriteria = ScoreCriteria.builder()
                .score(score)
                .criteria(complianceCriteria)
                .rate(complianceCriteriaRate)
                .createTime(now())
                .build();
        ScoreCriteria thirdScoreCriteria = ScoreCriteria.builder()
                .score(score)
                .criteria(executionCriteria)
                .rate(excecutionCriteriaRate)
                .createTime(now())
                .build();
        this.scoreCriteriaRepository.saveAll(List.of(firstScoreCriteria, secondScoreCriteria, thirdScoreCriteria));
        cleanData();
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
    public void processMassiveFile(MultipartFile file) throws IOException, ParseException {
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        final int NUM_COLUMN_A = 0;
        final int NUM_COLUMN_B = 1;
        final int NUM_COLUMN_C = 2;
        final int NUM_COLUMN_D = 3;
        final int NUM_COLUMN_E = 4;
        final int NUM_COLUMN_F = 5;
        final int NUM_COLUMN_G = 6;
        final int NUM_COLUMN_H = 7;
        final int NUM_COLUMN_I = 8;
        final int NUM_COLUMN_J = 9;
        final int NUM_COLUMN_K = 10;
        final int NUM_COLUMN_L = 11;
        boolean isNaturalPerson = false;
        // final String NIT = "1 NIT";
        final String RUT = "2 RUT - REGISTRO ÚNICO TRIBUTARIO";
        final String CC = "3 CÉDULA DE CIUDADANÍA";
        final String CE = "4 CÉDULA DE EXTRANJERÍA";

        System.out.println("\n============= Data Massive File ==============\n");

        Row row; // cada fila del excel
        int rownum = 1; // empieza en la fila 2
        boolean isRowEmpty = false; // para saber si la fila esta vacia

        while(!isRowEmpty) { // mientras la fila no este vacia
            row = sheet.getRow(rownum); // se obtiene la fila
            if( (row.getCell(NUM_COLUMN_A).toString()).isBlank() ) 
                isRowEmpty = true;
            // Comprobar si la celda en la coulmna L no es nula y no es NA
            if(!isRowEmpty && row.getCell(NUM_COLUMN_L)!=null && !row.getCell(NUM_COLUMN_L).toString().equals("NA")) { 
                // Comprobar si las celdas no son nulas, excepto las celdas con columnas F y G
                /*if(!(row.getCell(NUM_COLUMN_B).toString()).isBlank() && !(row.getCell(NUM_COLUMN_C).toString()).isBlank() && 
                    !(row.getCell(NUM_COLUMN_D).toString()).isBlank() && !(row.getCell(NUM_COLUMN_E).toString()).isBlank() && 
                    !(row.getCell(NUM_COLUMN_H).toString()).isBlank()
                ) {*/
                    System.out.println("\nPrinting row " + (rownum + 1) + "\n");

                    String reference = row.getCell(NUM_COLUMN_A).toString();
                    System.out.println("Reference: " + reference);
                    
                    LocalDateTime suscriptionDate = row.getCell(NUM_COLUMN_B).getLocalDateTimeCellValue();
                    if(suscriptionDate!=null) System.out.println("Susciption date: " + suscriptionDate);
                    
                    String contractType = row.getCell(NUM_COLUMN_C).toString();
                    if(!contractType.isBlank()) System.out.println("Contract type: " + contractType);
                    
                    String contractSubject = row.getCell(NUM_COLUMN_D).toString();
                    if(!contractSubject.isBlank()) System.out.println("Contract subject: " + contractSubject);
                    
                    String identificationType = row.getCell(NUM_COLUMN_E).toString();
                    if(!identificationType.isBlank()) System.out.println("Identification type: " + identificationType);

                    String identificationNumber="";

                    // Comprobar si es persona natural
                    if(identificationType.equals(RUT) || identificationType.equals(CC) || identificationType.equals(CE)) {
                        isNaturalPerson = true;
                    }

                    // Comprobar que, si es persona natural,
                    // que la celda en la columna F no sea nula
                    var cellF = row.getCell(NUM_COLUMN_F).toString();
                    if(isNaturalPerson == true && !cellF.isBlank()) {
                        if(!(row.getCell(NUM_COLUMN_G).toString()).isBlank()) {
                            System.out.println("Error: NIT is not blank");
                        } else {
                            identificationNumber = row.getCell(NUM_COLUMN_F).toString();
                        }
                    }
                    // Comprobar que, si no es persona natural, 
                    // que la celda en la columna G no sea nula
                    var cellG = row.getCell(NUM_COLUMN_G).toString();
                    if(isNaturalPerson == false && !cellG.isBlank()) {
                        if(!(row.getCell(NUM_COLUMN_F).toString()).isBlank()) {
                            System.out.println("Error: CC/RUT is not blank");
                        } else {
                            identificationNumber = row.getCell(NUM_COLUMN_G).toString();
                        }
                    }

                    if(!identificationNumber.isBlank()) System.out.println("Identification number: " + identificationNumber);
                    
                    String vendorName = row.getCell(NUM_COLUMN_H).toString();
                    if(!vendorName.isBlank()) System.out.println("Vendor name: " + vendorName);

                    String supervisorName = row.getCell(NUM_COLUMN_I).toString();
                    if(!supervisorName.isBlank()) System.out.println("Supervisor name: " + supervisorName);

                    LocalDateTime initialDate = row.getCell(NUM_COLUMN_J).getLocalDateTimeCellValue();
                    if(initialDate!=null) System.out.println("Initial date: " + initialDate);

                    LocalDateTime finalDate = row.getCell(NUM_COLUMN_K).getLocalDateTimeCellValue();
                    if(finalDate!=null) System.out.println("Final date: " + finalDate);

                    String contractEvaluation = row.getCell(NUM_COLUMN_L).toString();
                    System.out.println("Contract evaluation: " + contractEvaluation);
                    
                /* } else {
                    isRowEmpty = true;
                }*/
            }
            rownum++;
        }
        System.out.println("================= End =====================");
        if (workbook != null) workbook.close();
    }
}
