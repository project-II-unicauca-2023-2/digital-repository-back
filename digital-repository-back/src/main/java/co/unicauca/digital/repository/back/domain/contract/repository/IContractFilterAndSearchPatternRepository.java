package co.unicauca.digital.repository.back.domain.contract.repository;

import co.unicauca.digital.repository.back.domain.contract.model.ContractDtoFindContractualFolders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that manages the persistence of the contracts by a filter and a search pattern
 */
@Repository
public interface IContractFilterAndSearchPatternRepository extends JpaRepository<ContractDtoFindContractualFolders, Integer> {
    /**
     * Query find contracts by a filter and a search pattern
     */
    @Query(
            value =
                    "SELECT CON.id, CON.reference, MODA.name AS modality, CONTYPE.name AS contractType, VEN.identification AS vendor, YEAR(CON.signingDate) AS signingYear " +
                            "FROM contract CON, modality MODA, contracttype CONTYPE, modalitycontracttype CONTYPEMODA, vendor VEN " +
                            "WHERE CON.modalityContractTypeId = CONTYPEMODA.id and CON.vendorId = VEN.id and CONTYPEMODA.modalityId = MODA.id AND CONTYPEMODA.contractTypeId = CONTYPE.id AND  " +
                            "((:filter = 'REFERENCE' AND CON.reference LIKE CONCAT('%', :search, '%')) OR " +
                            "(:filter = 'MODALITY' AND MODA.name LIKE CONCAT('%', :search, '%')) OR " +
                            "(:filter = 'TYPE' AND CONTYPE.name LIKE CONCAT('%', :search, '%')) OR " +
                            "(:filter = 'VENDOR' AND VEN.identification LIKE CONCAT('%', :search, '%')) OR " +
                            "(:filter = 'SUPERSCRIBE-YEAR' AND YEAR(CON.signingDate) LIKE CONCAT('%', :search, '%')))",
            countQuery = "SELECT COUNT(*) " +
                    "FROM contract CON, modality MODA, contracttype CONTYPE, modalitycontracttype CONTYPEMODA, vendor VEN " +
                    "WHERE CON.modalityContractTypeId = CONTYPEMODA.id and CON.vendorId = VEN.id and CONTYPEMODA.modalityId = MODA.id AND CONTYPEMODA.contractTypeId = CONTYPE.id AND " +
                    "((:filter = 'REFERENCE' AND CON.reference LIKE CONCAT('%', :search, '%')) OR " +
                    "(:filter = 'MODALITY' AND MODA.name LIKE CONCAT('%', :search, '%')) OR " +
                    "(:filter = 'TYPE' AND CONTYPE.name LIKE CONCAT('%', :search, '%')) OR " +
                    "(:filter = 'VENDOR' AND VEN.identification LIKE CONCAT('%', :search, '%')) OR " +
                    "(:filter = 'SUPERSCRIBE-YEAR' AND YEAR(CON.signingDate) LIKE CONCAT('%', :search, '%')))"
            , nativeQuery = true)
    Page<ContractDtoFindContractualFolders> findByFilterAndSearchPattern(String filter, String search, Pageable pageable);
}
