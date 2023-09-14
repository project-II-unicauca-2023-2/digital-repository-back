package co.unicauca.digital.repository.back.domain.collection.repository;

import co.unicauca.digital.repository.back.domain.collection.model.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICollectionRepository extends JpaRepository<Collection,Integer> {

    Page<Collection> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM Collection  WHERE  contractId = :contractId AND ContractualDocumentId = :contractualDocumentId",nativeQuery = true)
    Collection findContractualDocumentAndContract(Integer contractId, Integer contractualDocumentId);
    Optional<Collection> findCollectionByContractIdAndContractualDocumentId(Integer contractId, Integer contractualDocumentId);

}