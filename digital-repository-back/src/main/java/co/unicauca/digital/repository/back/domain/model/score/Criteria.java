package co.unicauca.digital.repository.back.domain.model.score;

import lombok.*;

import javax.persistence.*;

/**
 * Class that defines an entity for the O/R mapping of the CRITERIA table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Criteria {
    /** Criteria Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Criteria Name */
    @Column(length = 250)
    private String name;

    /** Criteria Description */
    @Column(length = 500)
    private String description;

    /** Criteria Type */
    @Column(length = 250)
    private String criteriaType;

    /** Criteria ScoreCriteria */
    @OneToOne(mappedBy = "criteria", optional = false)
    @JoinColumn(name = "scorecriteriaId")
    private ScoreCriteria scoreCriteria;
}
