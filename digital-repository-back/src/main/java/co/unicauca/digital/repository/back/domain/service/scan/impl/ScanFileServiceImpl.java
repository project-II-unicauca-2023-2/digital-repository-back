package co.unicauca.digital.repository.back.domain.service.scan.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        System.out.println("============= Data Massive File ==============");

        final Cell cell207A = sheet.getRow(206).getCell(0);
        String reference = cell207A.toString();
        System.out.println("Reference: " + reference);

        final Cell cell206B = sheet.getRow(206).getCell(1);
        LocalDateTime stringSuscriptionDate = cell206B.getDateCellValue().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        System.out.println("String susciption date: " + stringSuscriptionDate);

        final Cell cell206C = sheet.getRow(206).getCell(2);
        String contractType = cell206C.toString();
        System.out.println("Contract type: " + contractType);

        final Cell cell206D = sheet.getRow(206).getCell(3);
        String contractSubject = cell206D.toString();
        System.out.println("Contract subject: " + contractSubject);

        final Cell cell206E = sheet.getRow(206).getCell(4);
        String identificationType = cell206E.toString();

        final Cell cell206F = sheet.getRow(206).getCell(5);
        String ccRut = cell206F.toString();

        final Cell cell206G = sheet.getRow(206).getCell(6);
        String nit = cell206G.toString();

        final Cell cell206H = sheet.getRow(206).getCell(7);
        String vendorName = cell206H.toString();

        final Cell cell206I = sheet.getRow(206).getCell(8);
        String supervisorName = cell206I.toString();

        final Cell cell206J = sheet.getRow(206).getCell(9);
        LocalDateTime initialDate = cell206J.getDateCellValue().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        final Cell cell206K = sheet.getRow(206).getCell(10);
        LocalDateTime finalDate = cell206K.getDateCellValue().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        final Cell cell206L = sheet.getRow(206).getCell(11);
        String contractEvaluation = cell206L.toString();

        System.out.println("Identification type: " + identificationType + "\n" +
                "CC/RUT: " + ccRut + "\n" +
                "NIT: " + nit + "\n" +
                "Vendor name: " + vendorName + "\n" +
                "Supervisor name: " + supervisorName + "\n" +
                "Initial date: " + initialDate + "\n" +
                "Final date: " + finalDate + "\n" +
                "Contract evaluation: " + contractEvaluation + "\n");
        if (workbook != null)
            workbook.close();
    }
}
