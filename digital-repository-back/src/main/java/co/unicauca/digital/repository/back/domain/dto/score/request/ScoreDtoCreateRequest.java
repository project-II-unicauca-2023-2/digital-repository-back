package co.unicauca.digital.repository.back.domain.dto.score.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the Vendor table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDtoCreateRequest {
    /** Score contractId */
    @NotNull(message = "{score.contractid.field.not.null}")
    @NotEmpty(message = "{score.contractid.field.not.empty}")
    private Integer contractId;

    /** Score total score */
    @NotNull(message = "{score.totalscore.field.not.null}") // cambiar
    @NotEmpty(message = "{score.totalscore.field.not.empty}")
    private Float totalScore;
}
