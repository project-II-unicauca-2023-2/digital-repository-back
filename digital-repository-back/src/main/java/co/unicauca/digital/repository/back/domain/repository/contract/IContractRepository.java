package co.unicauca.digital.repository.back.domain.repository.contract;


import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindContractualFoldersResponse;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

/**
 * Repository that manages the persistence of the Contract entity in the
 * database.
 */
@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer> {
    /**
     * Query find contract by reference
     */
    Optional<Contract> findByReference(String reference);

    @Query(value = "SELECT * FROM contract c WHERE c.reference = :reference AND YEAR(c.initialDate) = :year", nativeQuery = true)
    Optional<Contract> findByReferenceAndYear(@Param("reference") String reference, @Param("year") int year);
    Optional<List<Contract>> findByVendorId(int reference);

    /**
     * Query find contracts by a filter and a search pattern
     */
    // @Query(value = "SELECT CON.id, CON.reference, MODA.name AS modality,
    // CONTYPE.name AS contractType, VEN.identification AS vendor,
    // YEAR(CON.signingDate) AS signingYear "
    // +
    // "FROM contract CON, modality MODA, contracttype CONTYPE, modalitycontracttype
    // CONTYPEMODA, vendor VEN "
    // +
    // "WHERE CON.modalityContractTypeId = CONTYPEMODA.id and CON.vendorId = VEN.id
    // and CONTYPEMODA.modalityId = MODA.id AND CONTYPEMODA.contractTypeId =
    // CONTYPE.id AND "
    // +
    // "((:filter = 'REFERENCE' AND CON.reference LIKE CONCAT('%', :search, '%')) OR
    // " +
    // "(:filter = 'MODALITY' AND MODA.name LIKE CONCAT('%', :search, '%')) OR " +
    // "(:filter = 'TYPE' AND CONTYPE.name LIKE CONCAT('%', :search, '%')) OR " +
    // "(:filter = 'VENDOR' AND VEN.identification LIKE CONCAT('%', :search, '%'))
    // OR " +
    // "(:filter = 'SUPERSCRIBE-YEAR' AND YEAR(CON.signingDate) LIKE CONCAT('%',
    // :search, '%')))", countQuery = "SELECT COUNT(*) "
    // +
    // "FROM contract CON, modality MODA, contracttype CONTYPE, modalitycontracttype
    // CONTYPEMODA, vendor VEN "
    // +
    // "WHERE CON.modalityContractTypeId = CONTYPEMODA.id and CON.vendorId = VEN.id
    // and CONTYPEMODA.modalityId = MODA.id AND CONTYPEMODA.contractTypeId =
    // CONTYPE.id AND "
    // +
    // "((:filter = 'REFERENCE' AND CON.reference LIKE CONCAT('%', :search, '%')) OR
    // "
    // +
    // "(:filter = 'MODALITY' AND MODA.name LIKE CONCAT('%', :search, '%')) OR " +
    // "(:filter = 'TYPE' AND CONTYPE.name LIKE CONCAT('%', :search, '%')) OR " +
    // "(:filter = 'VENDOR' AND VEN.identification LIKE CONCAT('%', :search, '%'))
    // OR "
    // +
    // "(:filter = 'SUPERSCRIBE-YEAR' AND YEAR(CON.signingDate) LIKE CONCAT('%',
    // :search, '%')))", nativeQuery = true)
    // Page<ContractDtoFindContractualFoldersResponse>
    // findByFilterAndSearchPattern(String filter, String search,
    // Pageable pageable);

    @Query("SELECT new co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindContractualFoldersResponse"
            +
            "(CON.id, CON.reference, MODA.name AS modality, CONTYPE.name AS contractType, VEN.identification AS vendor, YEAR(CON.signingDate) AS signingYear) "
            +
            "FROM Contract CON, Modality MODA, ContractType CONTYPE, ModalityContractType CONTYPEMODA, Vendor VEN " +
            "WHERE CON.modalityContractType.id = CONTYPEMODA.id and " +
            "CON.vendor.id = VEN.id and " +
            "CONTYPEMODA.modality.id = MODA.id AND " +
            "CONTYPEMODA.contractType.id = CONTYPE.id AND " +
            "((:filter = 'REFERENCE' AND CON.reference LIKE CONCAT('%', :search, '%')) OR " +
            "(:filter = 'MODALITY' AND MODA.name LIKE CONCAT('%', :search, '%')) OR " +
            "(:filter = 'TYPE' AND CONTYPE.name LIKE CONCAT('%', :search, '%')) OR " +
            "(:filter = 'VENDOR' AND VEN.identification LIKE CONCAT('%', :search, '%')) OR " +
            "(:filter = 'SUPERSCRIBE-YEAR' AND YEAR(CON.signingDate) LIKE CONCAT('%', :search, '%')))")
    Page<ContractDtoFindContractualFoldersResponse> findByFilterAndSearchPattern(String filter, String search,
            Pageable pageable);


    //Query for average from contract description
    @Query(value = " SELECT AVG(sco.rate) FROM contract c INNER JOIN score sc ON c.id=sc.contract_id INNER JOIN scorecriteria sco " 
    + " ON sc.contract_id=sco.scoreId INNER JOIN modalitycontracttype m ON c.modalityContractTypeId=m.id INNER JOIN contracttype "
    + " con ON con.id=m.contractTypeId WHERE con.description =:description AND YEAR(c.initialDate) =:year "
    + " AND sc.createTime < sc.updateTime", nativeQuery = true)
    float getAverageByCategory(@Param("description") String description, @Param("year") int year);

    // Método para obtener contratos vencidos
    Optional<List<Contract>> findByFinalDateBefore(LocalDateTime currentDate);
}
