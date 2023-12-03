package co.unicauca.digital.repository.back.domain.repository.contractType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;

import java.util.List;
import java.util.Optional;

/**
 * Repository that manages the persistence of the Modality entity in the
 * database.
 */
@Repository
public interface IContractTypeRepository extends JpaRepository<ContractType, Integer> {
    /**
     * Query find Modality by name
     */
    Optional<ContractType> findByExternalCode(String externalCode);

    @Query("SELECT v.name FROM ContractType v WHERE v.description = :description")
    List<String> getSubCategoryByCategory(@Param("description") String description);

}