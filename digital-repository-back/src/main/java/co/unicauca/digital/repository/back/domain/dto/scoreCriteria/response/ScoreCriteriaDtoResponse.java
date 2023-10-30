package co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response;

import java.util.List;

import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the Criteria table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCriteriaDtoResponse {

    private List<ScoreCriteriaDto> listaScoreCriteria;
    /*Score*/
    private Float totalScore;
}