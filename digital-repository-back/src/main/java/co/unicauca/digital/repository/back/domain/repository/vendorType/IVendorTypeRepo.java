package co.unicauca.digital.repository.back.domain.repository.vendorType;

import co.unicauca.digital.repository.back.domain.dto.vendorType.response.vendorTypeDtoGetTypeResponse;
import co.unicauca.digital.repository.back.domain.dto.vendorType.response.vendorTypeDtoGetTypeSpecResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;


/**
 * Repository that manages the persistence of the VendorType entity in the database.
 */ 
@Repository
public interface IVendorTypeRepo extends JpaRepository<ContractType, Integer> {

    @Query("SELECT new co.unicauca.digital.repository.back.domain.dto.vendorType.response.vendorTypeDtoGetTypeResponse(v.name) FROM ContractType v WHERE v.externalCode = :code")
    vendorTypeDtoGetTypeResponse getTypeByMask(@Param("code") String code);
    
    @Query("SELECT new co.unicauca.digital.repository.back.domain.dto.vendorType.response.vendorTypeDtoGetTypeSpecResponse(v.name, v.description) FROM ContractType v WHERE v.externalCode = :code")
    vendorTypeDtoGetTypeSpecResponse getTypeAndSpecByMask(@Param("code") String code);

    
}
