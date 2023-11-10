package co.unicauca.digital.repository.back.domain.service.scan;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

public interface IScanFileService {
    void processFile(MultipartFile file) throws IOException, ParseException;
    void saveData();
    void processMassiveFile(MultipartFile file) throws IOException, ParseException;
}
