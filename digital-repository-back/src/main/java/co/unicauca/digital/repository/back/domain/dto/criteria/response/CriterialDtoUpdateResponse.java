package co.unicauca.digital.repository.back.domain.dto.criteria.response;

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
public class CriterialDtoUpdateResponse {

    /** Criteria Name */
    private String name;

    /** Criteria Description */
    private String description;

    /** Criteria Type */
    private String criteriaType;
}
