package co.unicauca.digital.repository.back.domain.internalNormative.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the INTERNAL NORMATIVE table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InternalNormativeDtoUpdateRequest {

    /** InternalNormative id */
    @NotNull(message = "{InternalNormative.ID.field.not.null}")
    @Positive(message = "{InternalNormative.ID.field.positive}")
    private Integer id;

    /** InternalNormative name */
    @NotNull(message = "{InternalNormative.name.field.not.null}")
    @NotEmpty(message = "{InternalNormative.name.field.not.empty}")
    private String name;

    /** InternalNormative isInForce */
    @NotNull(message = "{InternalNormative.isInForce.field.not.null}")
    private Boolean isInForce;

    /** InternalNormative initialTime */
    private LocalDateTime initialTime;

    /** InternalNormative finalTime */
    private LocalDateTime finalTime;

}
