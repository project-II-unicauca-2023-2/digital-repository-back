package co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCriteriaCalificationDomainDtoResponse{
    private List<ScoreCriteriaCalificationDomainDto> listCalificationDomain;
}