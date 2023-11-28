package co.unicauca.digital.repository.back.domain.service.scan;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IScanFileService {
    void processFile(MultipartFile file) throws IOException, ParseException;
    void saveData();
    List<String> processMassiveFile(MultipartFile file) throws IOException, ParseException;
    void saveMassiveData();
}
