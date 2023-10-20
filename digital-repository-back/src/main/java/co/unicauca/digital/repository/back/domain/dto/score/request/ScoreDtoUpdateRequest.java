package co.unicauca.digital.repository.back.domain.dto.score.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the Score table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDtoUpdateRequest {

    /** Score total score */
    @NotNull(message = "{score.totalscore.field.not.null}")
    @NotEmpty(message = "{score.totalscore.field.not.empty}")
    private Float totalScore;
}
