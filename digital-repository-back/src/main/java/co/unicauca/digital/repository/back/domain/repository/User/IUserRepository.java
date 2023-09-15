package co.unicauca.digital.repository.back.domain.repository.User;

import co.unicauca.digital.repository.back.domain.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages the persistence of the User entity in the database.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
}
