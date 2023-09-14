package co.unicauca.digital.repository.back.domain.contractType.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.unicauca.digital.repository.back.domain.contractType.model.ContractType;

import java.util.Optional;

/**
 * Repository that manages the persistence of the Modality entity in the database.
 */
@Repository
public interface IContractTypeRepository extends JpaRepository<ContractType, Integer>{
     /**
     * Query find Modality by name
     */
    Optional<ContractType> findByExternalCode(String externalCode);
}