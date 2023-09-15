package co.unicauca.digital.repository.back.domain.repository.vendor;

import co.unicauca.digital.repository.back.domain.model.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository that manages the persistence of the Vendor entity in the database.
 */
@Repository
public interface IVendorRepository extends JpaRepository<Vendor, Integer> {

    /**
     * Query find vendor by identification
     */
    Optional<Vendor> findByIdentification(String identification);

}
