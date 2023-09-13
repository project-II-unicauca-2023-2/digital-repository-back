package co.unicauca.digital.repository.back.domain.modality.repository;

import co.unicauca.digital.repository.back.domain.modality.model.Modality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository that manages the persistence of the Modality entity in the database.
 */
@Repository
public interface IModalityRepository extends JpaRepository<Modality, Integer>{
    
    /**
     * Query find Modality by name
     */
    Optional<Modality> findByExternalCode(String externalCode);
}
