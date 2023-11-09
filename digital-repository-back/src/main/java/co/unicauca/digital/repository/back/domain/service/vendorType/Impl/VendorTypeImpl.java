package co.unicauca.digital.repository.back.domain.service.vendorType.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.dto.vendorType.response.vendorTypeDtoGetTypeResponse;
import co.unicauca.digital.repository.back.domain.dto.vendorType.response.vendorTypeDtoGetTypeSpecResponse;
import co.unicauca.digital.repository.back.domain.repository.vendorType.IVendorTypeRepo;
import co.unicauca.digital.repository.back.domain.service.vendorType.IVendorTypeService;

@Service
public class VendorTypeImpl implements IVendorTypeService {

    @Autowired
    private IVendorTypeRepo vendorTypeRepo;
    

    @Override
    public ResponseEntity<vendorTypeDtoGetTypeResponse> getVendorType(String contractMask) {
        try {
            vendorTypeDtoGetTypeResponse response=vendorTypeRepo.getTypeByMask(contractMask);
            if (response.equals(null)) {
                throw new Exception();
            }
            return new ResponseEntity<vendorTypeDtoGetTypeResponse>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<vendorTypeDtoGetTypeResponse>(new vendorTypeDtoGetTypeResponse("Mascara no encontrada."),HttpStatus.OK);
        }
    }
   

    @Override
    public ResponseEntity<vendorTypeDtoGetTypeSpecResponse> getVendorTypeAndSpec(String contractMask) {
        return new ResponseEntity<vendorTypeDtoGetTypeSpecResponse>(vendorTypeRepo.getTypeAndSpecByMask(contractMask),HttpStatus.OK);
    }
    
}
