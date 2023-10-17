package co.unicauca.digital.repository.back.domain.model.score;

import lombok.*;

import javax.persistence.*;
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
}
