package co.unicauca.digital.repository.back.domain.internalNormative.repository;

import co.unicauca.digital.repository.back.domain.internalNormative.model.InternalNormative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that manages the persistence of the InternalNormative entity in the database.
 */
public interface IInternalNormativeRepository extends JpaRepository<InternalNormative, Integer> {
}
