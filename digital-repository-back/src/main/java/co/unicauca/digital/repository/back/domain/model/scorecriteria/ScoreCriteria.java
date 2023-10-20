package co.unicauca.digital.repository.back.domain.model.scoreCriteria;

import lombok.*;

import javax.persistence.*;

import co.unicauca.digital.repository.back.domain.model.criteria.Criteria;
import co.unicauca.digital.repository.back.domain.model.score.Score;

import java.time.LocalDateTime;

/**
 * Class that defines an entity for the O/R mapping of the SCORECRITERIA table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCriteria {
    /** ScoreCriteria Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** ScoreCriteria Rate */
    private Integer rate;

    /** ScoreCriteria create time */
    private LocalDateTime createTime;

    /** ScoreCriteria last update time */
    private LocalDateTime updateTime;

    /** ScoreCriteria Score */
    @ManyToOne(optional = false)
    @JoinColumn(name = "scoreId")
    private Score score;

    @OneToOne(optional = false)
    @JoinColumn(name = "criteriaId")
    private Criteria criteria;
}
