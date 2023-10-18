package co.unicauca.digital.repository.back.domain.repository.score;

import co.unicauca.digital.repository.back.domain.model.score.Score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository that manages the persistence of the Score entity in the database.
 */
@Repository
public interface IScoreRepository extends JpaRepository<Score, Integer> {
    /**
     * Query find Score by identification
     */
    Optional<Score> findByIdentification(Integer identification);
}