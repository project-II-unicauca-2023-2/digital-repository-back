package co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCriteriaDtoUpdateResponse {

    /** ScoreCriteria Id */
    private Integer id;

    /** ScoreCriteria rate */
    private Integer rate;

    /** ScoreCriteria criteriaId */
    private Integer criteriaId;

    /** ScoreCriteria update time */
    private LocalDateTime updateTime;
}
