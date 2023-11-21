package co.unicauca.digital.repository.back.domain.dto.scoreCriteria.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the ScoreCriteria table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCriteriaDtoCreate {
    
    /** ScoreCriteria criteriaId */
    @NotNull(message = "{scoreCriteria.criteriaId.field.not.null}")
    @NotEmpty(message = "{scoreCriteria.criteriaId.field.not.empty}")
    private Integer criteriaId;

    /** ScoreCriteria Rate */
    @NotNull(message = "{scoreCriteria.rate.field.not.null}")
    @NotEmpty(message = "{scoreCriteria.rate.field.not.empty}")
    private Integer rate;
}
