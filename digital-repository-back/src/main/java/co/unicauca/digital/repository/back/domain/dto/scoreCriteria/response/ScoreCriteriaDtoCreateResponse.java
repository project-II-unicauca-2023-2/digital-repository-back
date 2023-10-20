package co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCriteriaDtoCreateResponse {
    /** ScoreCriteria Id */
    private Integer id;

    /** ScoreCriteria rate */
    private Integer rate;

    /** ScoreCriteria scoreId */
    private Integer scoreId;

    /** ScoreCriteria criteriaId */
    private Integer criteriaId;

    /** ScoreCriteria create time */
    private LocalDateTime createTime;

    /** ScoreCriteria update time */
    private LocalDateTime updateTime;
}
