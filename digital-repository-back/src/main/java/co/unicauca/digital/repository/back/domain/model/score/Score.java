package co.unicauca.digital.repository.back.domain.model.score;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.scoreCriteria.ScoreCriteria;

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

    /**
     * Aqui se utilizo una relacion one to one con la estrategia de clave
     * compartida, la clave primaria de contrato es la misma clave primaria
     * de score, de esta manera funciona como primaria y foranea al tiempo.
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    /** Total Score */
    private Float totalScore;

    /** Score create time */
    private LocalDateTime createTime;

    /** Score last update time */
    private LocalDateTime updateTime;

    /** Score scoreCriteria */
    @OneToMany(mappedBy = "score")
    private List<ScoreCriteria> scoringCriteria;
}
