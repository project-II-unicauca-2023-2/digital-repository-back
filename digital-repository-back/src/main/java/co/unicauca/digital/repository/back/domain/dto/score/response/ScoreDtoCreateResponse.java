package co.unicauca.digital.repository.back.domain.dto.score.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDtoCreateResponse {
    /** Score contractId */
    private Integer contractId;

    /** Score total score */
    private Float totalScore;

    /** Vendor create time */
    private LocalDateTime createTime;
}
