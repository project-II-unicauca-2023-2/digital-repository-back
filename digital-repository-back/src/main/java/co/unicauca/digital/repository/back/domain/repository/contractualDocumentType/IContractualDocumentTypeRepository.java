package co.unicauca.digital.repository.back.domain.repository.contractualDocumentType;

import co.unicauca.digital.repository.back.domain.model.contractualDocumentType.ContractualDocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages the persistence of the ContractualDocumentType entity
 * in the database.
 */
@Repository
public interface IContractualDocumentTypeRepository extends JpaRepository<ContractualDocumentType, Integer> {
}
