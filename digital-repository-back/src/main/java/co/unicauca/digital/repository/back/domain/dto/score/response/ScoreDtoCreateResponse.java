package co.unicauca.digital.repository.back.domain.dto.score.response;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the Score table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDtoCreateResponse {
    /** Score Id */
    private Integer id;

    /** Score total score */
    private Float totalScore;

    /** Score create time */
    private LocalDateTime createTime;

    /** Score update time */
    private LocalDateTime updateTime;
}
