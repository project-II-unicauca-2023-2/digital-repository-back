package co.unicauca.digital.repository.back.domain.repository.contract;

import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository that manages the persistence of the Contract entity in the
 * database.
 */
@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer> {
    /**
     * Query find contract by reference
     */
    Optional<Contract> findByReference(String reference);

    @Query(value = "SELECT * FROM contract c WHERE c.reference = :reference AND YEAR(c.initialDate) = :year",nativeQuery = true)
    Optional<Contract> findByReferenceAndYear(@Param("reference") String reference, @Param("year") int year);
}
