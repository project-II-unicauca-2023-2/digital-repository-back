package co.unicauca.digital.repository.back.domain.service.scan.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.unicauca.digital.repository.back.domain.service.scan.IScanFileService;
import co.unicauca.digital.repository.back.domain.utilities.ExcelUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScanFileServiceImpl implements IScanFileService {

    private final ExcelUtils excelUtils;

    @Override
    public void processFile(MultipartFile file) throws IOException {
        try {
            InputStream is = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0); // Primera hoja del excel.
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            System.out.println("======= Printing Data ========");
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
            LocalDateTime evaluationDate = excelUtils.extractEvaluationDate(cell6C.toString());
            System.out.println("Evaluation date: " + evaluationDate);

            // Number and date cells
            final Cell cell8C = sheet.getRow(7).getCell(2);
            System.out.println("Number and date: " + cell8C.toString());

            // Vendor Name cells
            final Cell cell9D = sheet.getRow(8).getCell(3);
            String vendorName = cell9D.toString();
            System.out.println("Vendor name:" + vendorName);

            // Vendor nit cells
            final Cell cell9G = sheet.getRow(8).getCell(6);
            System.out.println("Vendor Nit: " + cell9G.toString());

            // Vendor C.C cells
            final Cell cell9J = sheet.getRow(8).getCell(9);
            Integer vendorCC = excelUtils.extractVendorCC(cell9J.toString());
            System.out.println("Vendor C.C: " + vendorCC);

            // Initial date cells
            final Cell cell10C = sheet.getRow(9).getCell(2);
            LocalDateTime initialDate = cell10C.getLocalDateTimeCellValue();
            System.out.println("Initial date: " + initialDate.toString());

            // Final date cells
            final Cell cell10I = sheet.getRow(9).getCell(8);
            LocalDateTime finalDate = cell10I.getLocalDateTimeCellValue();
            System.out.println("Final Date: " + finalDate.toString());

            // Contract subject cells
            final Cell cell11A = sheet.getRow(10).getCell(0);
            String contractSubject =  excelUtils.extractContractSubject(cell11A.toString());
            System.out.println("Contract Subject: " + contractSubject);

            // Vendor type cells
            // Goods
            final Cell cell16J = sheet.getRow(15).getCell(9);
            System.out.println("1. Por contrato de Suministro: " + cell16J.toString());
            final Cell cell17J = sheet.getRow(16).getCell(9);
            System.out.println("2. Por contrato de Arrendamiento: " + cell17J.toString());
            final Cell cell18J = sheet.getRow(17).getCell(9);
            System.out.println("3. Por contrato de Compraventa: " + cell18J.toString());
            //Services
            final Cell cell19J = sheet.getRow(18).getCell(9);
            System.out.println("1. Por contrato de Prestación de servicios: " + cell19J.toString());
            final Cell cell20J = sheet.getRow(19).getCell(9);
            System.out.println("2. Por contrato de Consultoría: " + cell20J.toString());
            final Cell cell21J = sheet.getRow(20).getCell(9);
            System.out.println("3. Por contrato de Interventoría: " + cell21J.toString());
            final Cell cell22J = sheet.getRow(21).getCell(9);
            System.out.println("4. Por contrato de Pasantía: " + cell22J.toString());
            final Cell cell23J = sheet.getRow(22).getCell(9);
            System.out.println("5. Por contrato de Suministro: " + cell23J.toString());
            final Cell cell24J = sheet.getRow(23).getCell(9);
            System.out.println("6. Por contrato de Judicatura: " + cell24J.toString());
            final Cell cell25J = sheet.getRow(24).getCell(9);
            System.out.println("7. Por contrato de Aprendizaje: " + cell25J.toString());

            // Firt Criteria scoring cells
            final Cell cell36A = sheet.getRow(35).getCell(0);
            String firstCriteriaType = cell36A.toString();
            final Cell cell37A = sheet.getRow(36).getCell(0);
            String firstCriteria = cell37A.toString();
            final Cell cell37E = sheet.getRow(36).getCell(4);
            String firstCriteriaRating = cell37E.toString();
            final Cell cell38A = sheet.getRow(37).getCell(0);
            String secondCriteria= cell38A.toString();
            final Cell cell38E = sheet.getRow(37).getCell(4);
            String secondCriteriaRating= cell38E.toString();
            final Cell cell39A = sheet.getRow(38).getCell(0);
            String thirdCriteria = cell39A.toString();
            final Cell cell39E = sheet.getRow(38).getCell(4);
            String thirdCriteriaRating = cell39E.toString();
            final Cell cell40E = sheet.getRow(39).getCell(4);
            Float firstCriteriaTypeTotal = excelUtils.evaluateFormula(cell40E, evaluator);

            System.out.println("Criteria type: " + firstCriteriaType + "\n" +
                firstCriteria + ": " + firstCriteriaRating + "\n" +
                secondCriteria + ": " + secondCriteriaRating + "\n" +
                thirdCriteria + ": " + thirdCriteriaRating + "\n" +
                "Average total: " + firstCriteriaTypeTotal);
            // Second criteria scoring cells
            final Cell cell36F = sheet.getRow(35).getCell(5);
            String secondCriteriaType = cell36F.toString();
            final Cell cell37F = sheet.getRow(36).getCell(5);
            String fourthCriteria = cell37F.toString();
            final Cell cell37J = sheet.getRow(36).getCell(9);
            String fourthCriteriaRating = cell37J.toString();
            final Cell cell38F = sheet.getRow(37).getCell(5);
            String fifthCriteria= cell38F.toString();
            final Cell cell38J = sheet.getRow(37).getCell(9);
            String fifthCriteriaRating= cell38J.toString();
            final Cell cell39F = sheet.getRow(38).getCell(5);
            String sixthCriteria = cell39F.toString();
            final Cell cell39J = sheet.getRow(38).getCell(9);
            String sixthCriteriaRating = cell39J.toString();
            final Cell cell40F = sheet.getRow(39).getCell(5);
            String seventhCriteria = cell40F.toString();
            final Cell cell40J = sheet.getRow(39).getCell(9);
            String seventhCriteriaRating = cell40J.toString();
            final Cell cell41J = sheet.getRow(40).getCell(9);
            //Float secondCriteriaTypeTotal = evaluateFormula(cell41J, evaluator);
            String secondCriteriaTypeTotal = cell41J.toString();
            System.out.println("Criteria type: " + secondCriteriaType + "\n" +
                fourthCriteria + ": " + fourthCriteriaRating + "\n" +
                fifthCriteria + ": " + fifthCriteriaRating + "\n" +
                sixthCriteria + ": " + sixthCriteriaRating + "\n" +
                seventhCriteria + ": " + seventhCriteriaRating + "\n" +
                "Average total: " + secondCriteriaTypeTotal);
            // Third criteria scoring cells
            final Cell cell42A = sheet.getRow(41).getCell(0);
            String thirdCriteriaType = cell42A.toString();
            final Cell cell43A = sheet.getRow(42).getCell(0);
            String eigthCriteria = cell43A.toString();
            final Cell cell43E = sheet.getRow(42).getCell(4);
            String eigthCriteriaRating = cell43E.toString();
            final Cell cell44A = sheet.getRow(43).getCell(0);
            String ninethCriteria= cell44A.toString();
            final Cell cell44E = sheet.getRow(43).getCell(4);
            String ninethCriteriaRating= cell44E.toString();
            final Cell cell45A = sheet.getRow(44).getCell(0);
            String tenthCriteria = cell45A.toString();
            final Cell cell45E = sheet.getRow(44).getCell(4);
            String tenthCriteriaRating = cell45E.toString();
            final Cell cell46E = sheet.getRow(45).getCell(4);
            Float thirdCriteriaTypeTotal = excelUtils.evaluateFormula(cell46E, evaluator);

            System.out.println("Criteria type: " + thirdCriteriaType + "\n" +
                eigthCriteria + ": " + eigthCriteriaRating + "\n" +
                ninethCriteria + ": " + ninethCriteriaRating + "\n" +
                tenthCriteria + ": " + tenthCriteriaRating + "\n" +
                "Average total: " + thirdCriteriaTypeTotal);
            // Total evaluation cells
            final Cell cell42J = sheet.getRow(41).getCell(9);
            Float totalValue = excelUtils.evaluateFormula(cell42J, evaluator);
            System.out.println("Total: " + totalValue);

            // Supervisor fullname cells
            final Cell cell48C = sheet.getRow(47).getCell(1);
            System.out.println("Supervisor fullname: " + cell48C.toString());

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
