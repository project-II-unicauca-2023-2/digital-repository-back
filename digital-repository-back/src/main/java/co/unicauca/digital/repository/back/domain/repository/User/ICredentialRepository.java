package co.unicauca.digital.repository.back.domain.repository.User;

import co.unicauca.digital.repository.back.domain.model.User.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages the persistence of the Credential entity in the
 * database.
 */
@Repository
public interface ICredentialRepository extends JpaRepository<Credential, Integer> {
}
