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
    private Integer Id;

    /** Score total score */
    private Float totalScore;

    /** Vendor update time */
    private LocalDateTime updateTime;
}
