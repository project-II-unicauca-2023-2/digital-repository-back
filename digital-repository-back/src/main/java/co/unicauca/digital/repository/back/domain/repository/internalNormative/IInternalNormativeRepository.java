package co.unicauca.digital.repository.back.domain.repository.internalNormative;

import co.unicauca.digital.repository.back.domain.model.internalNormative.InternalNormative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that manages the persistence of the InternalNormative entity in
 * the database.
 */
public interface IInternalNormativeRepository extends JpaRepository<InternalNormative, Integer> {
}
