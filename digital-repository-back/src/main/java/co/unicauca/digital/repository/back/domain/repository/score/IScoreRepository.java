package co.unicauca.digital.repository.back.domain.repository.score;

import co.unicauca.digital.repository.back.domain.model.score.Score;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages the persistence of the Score entity in the database.
 */
@Repository
public interface IScoreRepository extends JpaRepository<Score, Integer> {
    /**
     * Query find Score by Id
     */
    Optional<Score> findById(Integer Id);
}
