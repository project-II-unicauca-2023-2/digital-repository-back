package co.unicauca.digital.repository.back.domain.repository.modality;

import co.unicauca.digital.repository.back.domain.model.modality.Modality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository that manages the persistence of the Modality entity in the
 * database.
 */
@Repository
public interface IModalityRepository extends JpaRepository<Modality, Integer> {

    /**
     * Query find Modality by name
     */
    Optional<Modality> findByExternalCode(String externalCode);
}
