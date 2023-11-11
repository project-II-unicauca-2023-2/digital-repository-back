package co.unicauca.digital.repository.back.domain.service.vendorType;

import co.unicauca.digital.repository.back.domain.dto.aboutVendor.response.aboutVendorDto;
import co.unicauca.digital.repository.back.global.response.Response;

/**
 * Interface of servicer Vendor Type
 */
public interface IAboutVendorService {

    /**
     * 
     * @param contractMask Mask of contract
     * @return Type and spec of vendor according to contract
     */
    Response<aboutVendorDto> getAboutVendor(String contractMask);
    
}
