package co.unicauca.digital.repository.back.domain.controller.scan;

import java.io.IOException;
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
            } catch (IOException e) {
                responseMessages.add("Error al leer el archivo excel");
                // return new ResponseEntity<>("Error al leer el archivos excel", 
                //     HttpStatus.INTERNAL_SERVER_ERROR);
            }
            responseMessages.add(scanFileService.saveData());
        }
        return ResponseEntity.ok(responseMessages);
    }

    @PostMapping("/uploadMassiveExcel")
    public ResponseEntity<?> uploadMassiveExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Por favor seleccione un archivo Excel para cargar", HttpStatus.BAD_REQUEST);
        }
        List<UploadExcelFileResponse> responseMessages = new ArrayList<>();
        try {
            responseMessages = scanFileService.processMassiveFile(file);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al leer el archivo excel", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(responseMessages);
    }
}
