package co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ScoreCriteriaCalificationDomainDto {
    private String label;
    private int value;
}
