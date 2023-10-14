package co.unicauca.digital.repository.back.domain.service.scan;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IScanFileService {
    void processFile(MultipartFile file) throws IOException;
}
