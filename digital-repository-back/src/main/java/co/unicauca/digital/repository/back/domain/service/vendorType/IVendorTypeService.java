package co.unicauca.digital.repository.back.domain.service.vendorType;

import co.unicauca.digital.repository.back.domain.dto.vendorType.response.vendorTypeDtoGetTypeResponse;
import co.unicauca.digital.repository.back.domain.dto.vendorType.response.vendorTypeDtoGetTypeSpecResponse;


import org.springframework.http.ResponseEntity;

/**
 * Interface of servicer Vendor Type
 */
public interface IVendorTypeService {

    /**
     * 
     * @param contractMask Mask of contract
     * @return Type of vendor according to contract
     */
    ResponseEntity<vendorTypeDtoGetTypeResponse> getVendorType(String contractMask);

    /**
     * 
     * @param contractMask Mask of contract
     * @return Type and spec of vendor according to contract
     */
    ResponseEntity<vendorTypeDtoGetTypeSpecResponse> getVendorTypeAndSpec(String contractMask);
    
}
