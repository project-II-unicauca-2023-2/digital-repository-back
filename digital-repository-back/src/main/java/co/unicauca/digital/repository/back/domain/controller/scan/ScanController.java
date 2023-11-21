package co.unicauca.digital.repository.back.domain.controller.scan;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import co.unicauca.digital.repository.back.domain.service.scan.IScanFileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scanFile")
public class ScanController {

    private final IScanFileService scanFileService;

    @PostMapping("/uploadExcels")
    public ResponseEntity<String> uploadExcelFiles(@RequestParam("files") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Por favor seleccione al menos un archivo Excel para cargar.");
        }
        for (MultipartFile file : files) {
            try {
                scanFileService.processFile(file);
            } catch (IOException | ParseException e) {
                // e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al procesar archivos Excel.");
            }
            scanFileService.saveData();
        }
        return ResponseEntity.ok("Archivos Excel cargados y procesados con éxito.");
    }

    @PostMapping("/uploadMassiveExcel")
    public ResponseEntity<String> uploadMassiveExcelFile(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) {
            return ResponseEntity.badRequest()
                .body("Por favor seleccione un archivo Excel masivo para cargar.");
        }
        try {
            scanFileService.processMassiveFile(file);
        } catch (IOException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al procesar archivo Excel masivo.");
        }
        // scanFileService.saveData();
        return ResponseEntity.ok("Archivo Excel masivo cargado y procesado con éxito.");
    }
}
