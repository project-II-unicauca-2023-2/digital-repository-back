package co.unicauca.digital.repository.back.domain.service.scan;

import org.springframework.web.multipart.MultipartFile;

import co.unicauca.digital.repository.back.domain.dto.scan.UploadExcelFileResponse;

import java.io.IOException;
import java.util.List;

public interface IScanFileService {
    void processFile(MultipartFile file) throws IOException;
    UploadExcelFileResponse saveData();
    List<String> processMassiveFile(MultipartFile file) throws IOException;
}
