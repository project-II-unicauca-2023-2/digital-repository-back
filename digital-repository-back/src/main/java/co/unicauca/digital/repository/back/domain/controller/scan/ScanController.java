package co.unicauca.digital.repository.back.domain.controller.scan;

import java.io.IOException;

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

        try {
            for (MultipartFile file : files) {
                scanFileService.processFile(file);
            }
            return ResponseEntity.ok("Archivos Excel cargados y procesados con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar archivos Excel: " + e.getMessage());
        }
    }
}
