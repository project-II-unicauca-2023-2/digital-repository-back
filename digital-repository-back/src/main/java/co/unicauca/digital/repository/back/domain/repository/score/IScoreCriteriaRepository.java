package co.unicauca.digital.repository.back.domain.repository.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.unicauca.digital.repository.back.domain.model.score.ScoreCriteria;

@Repository
public interface IScoreCriteriaRepository extends JpaRepository<ScoreCriteria,Integer>{
    /**
     * Soon queries necessary to connect to the database
     */
}
