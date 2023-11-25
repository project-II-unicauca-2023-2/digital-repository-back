package co.unicauca.digital.repository.back.domain.repository.score;

import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.score.Score;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages the persistence of the Score entity in the database.
 */
@Repository
@Transactional
public interface IScoreRepository extends JpaRepository<Score, Integer> {
    /**
     * Query find Score by Id
     */
    Optional<Score> findById(Integer id);

    @Modifying
    @Query(value = "UPDATE score SET totalScore = :totalScore WHERE contract_id = :contractId", nativeQuery = true)
    void updateByContractId(Float totalScore, Integer contractId);

    Optional<Score> findByContract(Contract contract);
}
