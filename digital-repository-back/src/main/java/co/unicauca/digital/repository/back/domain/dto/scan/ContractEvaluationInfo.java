package co.unicauca.digital.repository.back.domain.dto.scan;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ContractEvaluationInfo(
    String vendorName,
    String identification,
    LocalDateTime initialDate,
    LocalDateTime finalDate,
    String subject,
    Integer contractTypeId,
    Integer qualityRate,
    Integer complianceRate,
    Integer excecutionRate,
    Float totalScore
) {}
