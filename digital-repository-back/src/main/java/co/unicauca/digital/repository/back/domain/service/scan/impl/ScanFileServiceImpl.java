package co.unicauca.digital.repository.back.domain.service.scan.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;

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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScanFileServiceImpl implements IScanFileService {

    private final ExcelUtils excelUtils;
    private final IScoreRepository scoreRepository;
    private final IScoreCriteriaRepository scoreCriteriaRepository;
    private final ICriteriaRepository criteriaRepository;
    private final IContractRepository contractRepository;

    @Override
    public void processFile(MultipartFile file) throws IOException {
        try {
            InputStream is = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0); // Primera hoja del excel.
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            System.out.println("===================== Data Evaluacion Proveedores V2 =============");
            // Contract reference cells
            final Cell cell4A = sheet.getRow(3).getCell(0);
            String reference = excelUtils.extractReference(cell4A.toString());
            System.out.println("Reference: " + reference);

            // Evaluation version cells
            final Cell cell4D = sheet.getRow(3).getCell(3);
            Integer version = excelUtils.extractVersion(cell4D.toString());
            System.out.println("Version: " + version);

            // Evaluation update date cells
            final Cell cell4H = sheet.getRow(3).getCell(7);
            LocalDateTime updateDate = excelUtils.extractUpdateDate(cell4H.toString());
            System.out.println("Evaluation update date: " + updateDate);

            // Evaluation date cells
            final Cell cell6C = sheet.getRow(5).getCell(2);
            if(cell6C != null){
            LocalDateTime evaluationDate = excelUtils.extractEvaluationDate(cell6C.toString());
            System.out.println("Evaluation date: " + evaluationDate);
            }

            // Number and date cells
            final Cell cell8C = sheet.getRow(7).getCell(2);
            System.out.println("Number and date: " + cell8C.toString());
            String[] numberAndDate =cell8C.toString().split(" ");
            String numberReference = numberAndDate[0];
            var contract = this.contractRepository.findByReference(numberReference).orElseThrow();
            System.out.println("References Contract" + contract.getReference());
            // Vendor Name cells
            final Cell cell9C = sheet.getRow(8).getCell(2);
            String vendorName = cell9C.toString();
            System.out.println("Vendor name:" + vendorName);

            // type identification 
            final Cell  cell9H = sheet.getRow(8).getCell(7);
            String identificationType = cell9H.toString();
            System.out.println("Identification type:" + identificationType);
            // Vendor cc,nit O identification cells
            final Cell cell9J = sheet.getRow(8).getCell(9);
            int vendorIdentif = (int ) Double.parseDouble(cell9J.toString());
            System.out.println("Vendor Identification: " + vendorIdentif);

            // Initial date cells
            final Cell cell10C = sheet.getRow(9).getCell(2);
            if(cell10C.getLocalDateTimeCellValue() != null){
            LocalDateTime initialDate = cell10C.getLocalDateTimeCellValue();
            System.out.println("Initial date: " + initialDate.toString());
            }

            // Final date cells
            final Cell cell10I = sheet.getRow(9).getCell(8);
            if(cell10I.getLocalDateTimeCellValue() != null){
            LocalDateTime finalDate = cell10I.getLocalDateTimeCellValue();
            System.out.println("Final Date: " + finalDate.toString());
            }
            // Contract subject cells   
            final Cell cell11A = sheet.getRow(10).getCell(0);
            String contractSubject =  excelUtils.extractContractSubject(cell11A.toString());
            System.out.println("Contract Subject: " + contractSubject);

            // Vendor type cells
            // Goods
            final Cell cell16J = sheet.getRow(15).getCell(9);
            System.out.println("1. Por contrato de Compraventa: " + cell16J.toString());
            final Cell cell17J = sheet.getRow(16).getCell(9);
            System.out.println("2. Por contrato de Suministro: " + cell17J.toString());
            final Cell cell18J = sheet.getRow(17).getCell(9);
            System.out.println("3. Por contrato de orden de compra: " + cell18J.toString());
            //Services
            final Cell cell19J = sheet.getRow(18).getCell(9);
            System.out.println("1. Por contrato de Prestación de servicios: " + cell19J.toString());
            final Cell cell20J = sheet.getRow(19).getCell(9);
            System.out.println("2. Por contrato de Consultoría: " + cell20J.toString());
            final Cell cell21J = sheet.getRow(20).getCell(9);
            System.out.println("3. Por contrato de Suministro: " + cell21J.toString());
            final Cell cell22J = sheet.getRow(21).getCell(9);
            System.out.println("4. Por contrato de arrendamiento: " + cell22J.toString());
            final Cell cell23J = sheet.getRow(22).getCell(9);
            System.out.println("5. Por contrato de pasantia: " + cell23J.toString());
            final Cell cell24J = sheet.getRow(23).getCell(9);
            System.out.println("6. Por contrato de Judicatura: " + cell24J.toString());
            final Cell cell25J = sheet.getRow(24).getCell(9);
            System.out.println("7. Por contrato de Aprendizaje: " + cell25J.toString());
            final Cell cell26J = sheet.getRow(25).getCell(9);
            System.out.println("7. Por contrato de Obra: " + cell26J.toString());
            // Firt Criteria (Quality) scoring cells
            final Cell cell45A = sheet.getRow(44).getCell(0);
            String firstCriteriaType = cell45A.toString();
            final Cell cell46C = sheet.getRow(45).getCell(2);
            Integer qualityCriteriaRate = Integer.parseInt(cell46C.toString());
            // Second Criteria (Cumplimiento) scoring cellls
            final Cell cell45E = sheet.getRow(44).getCell(4);
            String secondCriteria= cell45E.toString();
            final Cell cell46G = sheet.getRow(45).getCell(6);
            Integer complianceCriteriaRate= Integer.parseInt(cell46G.toString());
            // Second Criteria (execute) scoring cellls
            final Cell cell45H = sheet.getRow(44).getCell(7);
            String thirdCriteria = cell45H.toString();
            final Cell cell46J = sheet.getRow(45).getCell(9);
            Integer excecutionCriteriaRate = Integer.parseInt(cell46J.toString());
            
            String[] vendorTypes = {cell16J.toString(),cell17J.toString(),cell18J.toString(),cell19J.toString(),cell20J.toString()
                ,cell21J.toString(),cell22J.toString(),cell23J.toString(),cell24J.toString()
                ,cell25J.toString(),cell26J.toString()}; 
            // Convertir el array en una lista y encontrar la posición de 'x'
            int position = Arrays.asList(vendorTypes).indexOf("x");
            // Declarar una variable para almacenar el resultado
            String criteriaType;
            // Aplicar la lógica condicional para asignar los valores correspondientes
            if (position >= 0 && position <= 2) {
                criteriaType = "bienes";
            } else if (position >= 3 && position <= 9) {
                criteriaType = "servicios";
            } else if (position == 10) {
                criteriaType = "obras";
            } else {
                criteriaType = "No se encontró 'x' en la lista dentro de los límites especificados.";
            }
            // Imprimir el resultado o realizar cualquier otra operación necesaria
            System.out.println("El criteria type es: " + criteriaType);

            var qualityCriteria = this.criteriaRepository.findByNameAndCriteriaType("Calidad", criteriaType).orElseThrow(); // TODO handle exception
            var executionCriteria = this.criteriaRepository.findByNameAndCriteriaType("Ejecucion", criteriaType).orElseThrow(); // TODO handle exception
            var complianceCriteria = this.criteriaRepository.findByNameAndCriteriaType("Cumplimiento", criteriaType).orElseThrow(); // TODO handle exception                     
            // System.out.println("quialit criteria " + qualityCriteria.getDescription());
            // System.out.println("execution criteria " + executionCriteria.getDescription());
            // System.out.println("compliance criteria " + complianceCriteria.getDescription());

            System.out.println("first Criteria type: " + firstCriteriaType +
                 ": " + qualityCriteriaRate + "\n" +
                "second Criteria type: "+secondCriteria + ": " + complianceCriteriaRate + "\n" +
                "third Criteria type: "+thirdCriteria + ": " + excecutionCriteriaRate+"\n" );
            
         
            // Total evaluation cells
            final Cell cell47J = sheet.getRow(46).getCell(9);
            Float totalValue = excelUtils.evaluateFormula(cell47J, evaluator);
            System.out.println("Total Evaluation: " + totalValue + "\n");

            // Supervisor data cells
            final Cell cell49B = sheet.getRow(48).getCell(1);
            String nameSupervisor = cell49B.toString();
            final Cell cell49F = sheet.getRow(48).getCell(5);
            String signatureSuoervisor = cell49F.toString();
            final Cell cell49H = sheet.getRow(48).getCell(7);
            String signatureContratist = cell49H.toString();
            System.out.println("Supervisor fullname: " + nameSupervisor+"\n"
            + "signature supervisor"+ signatureSuoervisor+"\n"
            +"Signature Contratist"+ signatureContratist);

            // buy orders cell
            final Cell cell51C = sheet.getRow(50).getCell(2);
            String nameProfessionalInventary = cell51C.toString();
            final Cell cell51H = sheet.getRow(50).getCell(7);
            String signatureProfessionalInventary = cell51H.toString(); 
            System.out.println("Ordenes de compra(Solo si aplica)"+"\n"+" Nombre profesional especializado"+nameProfessionalInventary
            +"\n"+"signature Personal especializado inventario: "+signatureProfessionalInventary);

            Score score = Score.builder()
            .totalScore(totalValue).contract(contract)
            .createTime(LocalDateTime.now()).build();
            this.scoreRepository.save(score);

            ScoreCriteria firstScoreCriteria = ScoreCriteria.builder()
            .score(score).criteria(qualityCriteria).rate(qualityCriteriaRate)
            .createTime(LocalDateTime.now()).build();

            ScoreCriteria secondScoreCriteria = ScoreCriteria.builder()
            .score(score).criteria(complianceCriteria).rate(complianceCriteriaRate)
            .createTime(LocalDateTime.now()).build();

            ScoreCriteria thirdScoreCriteria = ScoreCriteria.builder()
            .score(score).criteria(executionCriteria).rate(excecutionCriteriaRate)
            .createTime(LocalDateTime.now()).build();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
