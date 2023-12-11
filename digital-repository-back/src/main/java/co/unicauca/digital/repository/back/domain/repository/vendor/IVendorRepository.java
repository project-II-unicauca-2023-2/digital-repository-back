package co.unicauca.digital.repository.back.domain.repository.vendor;

import co.unicauca.digital.repository.back.domain.model.vendor.Vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    //Query to obtain a supplier with the requested data
    @Query(value = "SELECT v.id ,v.name, v.identification, ( SELECT COUNT(*) FROM contract c WHERE c.vendorId = v.id "
    +" AND YEAR(c.initialDate) = :year ) as cantidadContratos, (SELECT COUNT(*) FROM contract c WHERE "
    +" c.vendorId = v.id) as cantidadTotalContratos, v.score FROM vendor v INNER JOIN contract c ON c.vendorId = v.id "
    +" INNER JOIN score s ON c.id = s.contract_id WHERE c.id = :idContract AND YEAR(c.initialDate) = :year GROUP BY v.id", nativeQuery = true)
    List<String> findVendorData(@Param("year") int year, @Param("idContract") int idContract);

    @Query(value = "SELECT AVG(sc.totalScore) FROM contract c INNER JOIN score sc ON c.id = sc.contract_id INNER JOIN"
    +" vendor v ON c.vendorId = v.id WHERE YEAR(c.initialDate) = 2023 AND v.id =:idVendor", nativeQuery = true)
    float calculateAverageYearVendor(@Param("idVendor") int idVendor);

}
