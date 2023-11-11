package co.unicauca.digital.repository.back.domain.repository.scoreCriteria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.unicauca.digital.repository.back.domain.model.scoreCriteria.ScoreCriteria;

import java.util.Optional;

/**
 * Repository that manages the persistence of the Score entity in the database.
 */
@Repository
public interface IScoreCriteriaRepository extends JpaRepository<ScoreCriteria, Integer> {
    /**
     * Query find ScoreCriteria by Id
     */
    Optional<ScoreCriteria> findById(Integer id);
}