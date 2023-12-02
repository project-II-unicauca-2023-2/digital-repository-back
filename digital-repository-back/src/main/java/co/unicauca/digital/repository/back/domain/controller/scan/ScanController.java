package co.unicauca.digital.repository.back.domain.controller.scan;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import co.unicauca.digital.repository.back.domain.dto.scan.UploadExcelFileResponse;
import co.unicauca.digital.repository.back.domain.service.scan.IScanFileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scanFile")
public class ScanController {

    private final IScanFileService scanFileService;

    @PostMapping("/uploadExcels")
    public ResponseEntity<?> uploadExcelFiles(@RequestParam("files") List<MultipartFile> files) {
        if (files.isEmpty() || files.get(0).isEmpty()) {
            return new ResponseEntity<>("Por favor seleccione un archivo Excel para cargar", HttpStatus.BAD_REQUEST);
        }
        var responseMessages = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                scanFileService.processFile(file);
            } catch (IOException | ParseException e) {
                return new ResponseEntity<>("Error al guardar el archivo excel en la base de datos", 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }
            responseMessages.add(scanFileService.saveData());
            // for (var responseMessage : scanFileService.saveData()) {
            //     responseMessages.add(string);    
            // }
        }
        return ResponseEntity.ok(responseMessages);
    }

    @PostMapping("/uploadMassiveExcel")
    public ResponseEntity<?> uploadMassiveExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Por favor seleccione un archivo Excel para cargar", HttpStatus.BAD_REQUEST);
        }
        List<String> mensajesProceso = new ArrayList<>();
        try {
            mensajesProceso = scanFileService.processMassiveFile(file);
        } catch (IOException | ParseException e) {
            return new ResponseEntity<>("Error al guardar el archivo excel en la base de datos", 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(mensajesProceso);
    }
}
