package co.unicauca.digital.repository.back.domain.modalityContractType.repository;

import co.unicauca.digital.repository.back.domain.modalityContractType.model.ModalityContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository that manages the persistence of the ModalityContractType entity in the database.
 */
@Repository
public interface IModalityContractTypeRepository extends JpaRepository<ModalityContractType, Integer> {

    @Query(value = "FROM ModalityContractType WHERE contractTypeId = ?1 and modalityId = ?2")
    Optional<ModalityContractType> findByContractModality(Integer contractType, Integer modality);

}
