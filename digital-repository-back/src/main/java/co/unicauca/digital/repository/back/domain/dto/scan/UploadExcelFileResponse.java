package co.unicauca.digital.repository.back.domain.dto.scan;

import java.util.List;

import lombok.Builder;

@Builder
public record UploadExcelFileResponse(
    String reference,
    List<String> messages,
    ContractEvaluationInfo contractInfo
) 
{}