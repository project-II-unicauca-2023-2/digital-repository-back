package co.unicauca.digital.repository.back.domain.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class ExcelUtils {

    public String extractReferenceNumber(String reference){
        String[] numberAndDate = reference.split(" ");
        return numberAndDate[0];
    }

    public Integer extractCriteriaRate(String criteriaRateString) {
        if(criteriaRateString.contains(".")) {
            Double criteriaRate = Double.parseDouble(criteriaRateString);
            return criteriaRate.intValue();
        }
        return Integer.parseInt(criteriaRateString);
    }

    public String extractCode(String referenceCode) {
        // Verificar si la cadena contiene ":"
        if (referenceCode.contains(":")) {
            // Encontrar la posición de ":"
            int indexOfColon = referenceCode.indexOf(":");
            // Extraer la parte después de ":"
            int beginIndex = indexOfColon + 2;
            return referenceCode.substring(beginIndex);
        } else {
            // Si no hay ":", devolver la cadena original.
            return referenceCode;
        }
    }
    
    public String extractContractSubject(String contractSubject) {
        // Verificar si la cadena contiene ":" y "\n"
        if (contractSubject.contains(":") && contractSubject.contains("\n")) {
            // Encontrar la posición de "\n"
            int indexOfLF = contractSubject.indexOf("\n");
            // Extraer la parte después de "\n"
            int beginIndex = indexOfLF + 1;
            return contractSubject.substring(beginIndex);
        } else {
            // Si no hay ":" y "\n", devolver la cadena original.
            return contractSubject;
        }
    }
    
    public Integer extractVersion(String version) {
        // Verificar si la cadena contiene ":"
        if (version.contains(":")) {
            // Encontrar la posición de ":"
            int indexOfColon = version.indexOf(":");
            // Extraer la parte después de ":"
            int beginIndex = indexOfColon + 2;
            return Integer.parseInt(version.substring(beginIndex));
        } else {
            // Si no hay ":", devolver la cadena original.
            return -1;
        }
    }

    public Integer extractVendorCC(String vendorCC) {
        // Eliminar los puntos de la cadena
        String cleanedVendorCC = vendorCC.replace(".", "");
        String numericVendorCC = cleanedVendorCC.replace(" ", "");
        try {
            // Intentar convertir la cadena en un número Integer
            Integer parsedVendorCC = Integer.parseInt(numericVendorCC);
            return parsedVendorCC;
        } catch (NumberFormatException e) {
            // Manejar la excepción si la cadena no es un número válido
            e.printStackTrace();
            return -1; // Opcional: Puedes devolver un valor predeterminado o manejar el error de otra manera.
        }
    }
    
    public LocalDateTime extractEvaluationUpdateDate(String updateDate) throws ParseException{
        // Verificar si la cadena contiene ":"
        if (updateDate.contains(":")) {
            // Encontrar la posición de ":"
            int indexOfColon = updateDate.indexOf(":");
            // Extraer la parte después de ":"
            int beginIndex = indexOfColon + 2;
            String dateToFormat = updateDate.substring(beginIndex);
            SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = originalFormat.parse(dateToFormat);
            String reverseDate = targetFormat.format(date);
            // System.out.println("Fecha invertida: " + reverseDate);
            
            return LocalDateTime.parse(reverseDate.concat("T00:00:00"));
        } else {
            // Si no hay ":", devolver la cadena original.
            return null;
        }
    }

    public LocalDateTime extractEvaluationDate(String evaluationDate){
        String[] evaluationDatesSplit = evaluationDate.split(" ");
        String year = evaluationDatesSplit[3];
        String monthName = evaluationDatesSplit[1];
        String day = evaluationDatesSplit[0];
        Integer monthNum=0;
        switch (MonthEnum.valueOf(monthName)) {
            case ENERO:
                monthNum=1;
                break;
            case FEBRERO:
                monthNum=2;
                break;
            case MARZO:
                monthNum=3;
                break;
            case ABRIL:
                monthNum=4;
                break;
            case MAYO:
                monthNum=5;
                break;
            case JUNIO:
                monthNum=6;
                break;
            case JULIO:
                monthNum=7;
                break;
            case AGOSTO:
                monthNum=8;
                break;
            case SEPTIEMBRE:
                monthNum=9;
                break;
            case OCTUBRE:
                monthNum=10;
                break;
            case NOVIEMBRE:
                monthNum=11;
                break;
            case DICIEMBRE:
                monthNum=12;
                break;
        }

        String month;
        if(Integer.toString(monthNum).length()==1){
            month = "0" + monthNum;
        }else{
            month = Integer.toString(monthNum);
        }
        String date = year + "-" + month + "-" + day + "T00:00:00";

        return LocalDateTime.parse(date);
    }

    public Float evaluateFormula(Cell cell, FormulaEvaluator evaluator) {
        if (cell != null) {
            if (cell.getCellType() == CellType.FORMULA) {
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue.getCellType() == CellType.NUMERIC) {
                    return (float) cellValue.getNumberValue(); // Convertir a Float
                }
            }
        }
        return null; // Valor nulo si la celda no contiene una fórmula numérica.
    }
}
