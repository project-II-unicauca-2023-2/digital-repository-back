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
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ExcelUtils {

    public String determineVendorType(List<String> vendorTypes) {
        //int position = vendorTypes.indexOf("x");
        int position = -1;
        for (String type : vendorTypes) {
            if (type.toLowerCase().contains("x".toLowerCase())) {
                position = vendorTypes.indexOf(type);
                break;
            }
        }
        String criteriaType = null;
        if (position >= 0 && position <= 2) {
            criteriaType = "Bienes";
        } else if (position >= 3 && position <= 9) {
            criteriaType = "Servicios";
        } else if (position == 10) {
            criteriaType = "Obras";
        }
        return criteriaType;
    }

    public String extractReferenceNumber(String reference){
        String[] numberAndDate = reference.split(" ");
        return numberAndDate[0];
    }

    public Integer extractIntegerValue(String value) {
        if(value.isBlank()) {
            return 0;
        }
        if(!Pattern.matches("^\\d+$", value)) {
            return -1;
        }
        if(value.contains(".")) {
            Double doubleValue = Double.parseDouble(value);
            return doubleValue.intValue();
        }
        return Integer.parseInt(value);
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
        Integer monthNum = (MonthEnum.valueOf(monthName)).getNum();
        String month;
        if(Integer.toString(monthNum).length() == 1){
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
