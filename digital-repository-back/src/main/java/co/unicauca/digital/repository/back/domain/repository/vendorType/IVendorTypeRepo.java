package co.unicauca.digital.repository.back.domain.repository.vendorType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.unicauca.digital.repository.back.domain.dto.aboutVendor.response.aboutVendorDto;
import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;


/**
 * Repository that manages the persistence of the VendorType entity in the database.
 */ 
@Repository
public interface IVendorTypeRepo extends JpaRepository<ContractType, Integer> {
    
    @Query("SELECT new co.unicauca.digital.repository.back.domain.dto.aboutVendor.response.aboutVendorDto(v.id, v.description, v.name) FROM ContractType v WHERE v.externalCode = :code")
    aboutVendorDto getTypeAndSpecByMask(@Param("code") String code);

    
}
