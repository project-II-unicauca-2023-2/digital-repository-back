package co.unicauca.digital.repository.back.domain.dto.score.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDtoUpdateResponse {
    /** Score Id */
    private Integer id;

    /** Score total score */
    private Float totalScore;

    /** Score update time */
    private LocalDateTime updateTime;
}
