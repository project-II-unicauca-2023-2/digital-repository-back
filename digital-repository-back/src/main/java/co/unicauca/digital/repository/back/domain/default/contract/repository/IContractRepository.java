package co.unicauca.digital.repository.back.domain.contract.repository;

import co.unicauca.digital.repository.back.domain.contract.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository that manages the persistence of the Contract entity in the database.
 */
@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer> {
    /**
     * Query find contract by reference
     */
    Optional<Contract> findByReference(String reference);
}
