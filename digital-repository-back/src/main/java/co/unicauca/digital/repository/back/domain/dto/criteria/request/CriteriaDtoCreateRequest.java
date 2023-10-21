package co.unicauca.digital.repository.back.domain.dto.criteria.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the Criteria table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriteriaDtoCreateRequest {
    /** Criteria Name */
    @NotNull(message = "{criteria.name.field.not.null}")
    @NotEmpty(message = "{criteria.name.field.not.empty}")
    private String name;

    /** Criteria Description */
    @NotNull(message = "{criteria.description.field.not.null}")
    @NotEmpty(message = "{criteria.description.field.not.empty}")
    private String description;

    /** Criteria Type */
    @NotNull(message = "{criteria.description.field.not.null}")
    @NotEmpty(message = "{criteria.description.field.not.empty}")
    private String criteriaType;
}
