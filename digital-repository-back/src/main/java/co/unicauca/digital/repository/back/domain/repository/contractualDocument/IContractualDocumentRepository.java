package co.unicauca.digital.repository.back.domain.repository.contractualDocument;

import co.unicauca.digital.repository.back.domain.model.contractualDocument.ContractualDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages the persistence of the ContractualDocument entity in
 * the database.
 */
@Repository
public interface IContractualDocumentRepository extends JpaRepository<ContractualDocument, Integer> {
}
