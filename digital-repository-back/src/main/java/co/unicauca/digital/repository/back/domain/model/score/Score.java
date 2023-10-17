package co.unicauca.digital.repository.back.domain.model.score;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import co.unicauca.digital.repository.back.domain.model.contract.Contract;

/**
 * Class that defines an entity for the O/R mapping of the SCORE table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {
    /** Score Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Total Score */
    private Float TotalScore;

    /** Score create time */
    private LocalDateTime createTime;

    /** Score last update time */
    private LocalDateTime updateTime;

    /** Score contract */
    @OneToOne(mappedBy = "score")
    @JoinColumn(name = "contractId")
    private Contract contract;

    /** Score scoreCriteria */
    @OneToMany(mappedBy = "score")
    private List<ScoreCriteria> scoringCriteria;
}
