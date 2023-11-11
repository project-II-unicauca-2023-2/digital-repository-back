package co.unicauca.digital.repository.back.domain.service.vendorType.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.dto.aboutVendor.response.aboutVendorDto;
import co.unicauca.digital.repository.back.domain.repository.vendorType.IVendorTypeRepo;
import co.unicauca.digital.repository.back.domain.service.criteria.ICriteriaService;
import co.unicauca.digital.repository.back.domain.service.vendorType.IAboutVendorService;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;

@Service
public class AboutVendorImpl implements IAboutVendorService {

    @Autowired
    private IVendorTypeRepo vendorTypeRepo;   
    @Autowired
    private ICriteriaService criteriaService;

    @Override
    public Response<aboutVendorDto> getAboutVendor(String contractMask) {
        String[] parts = contractMask.split("/");
        aboutVendorDto aboutVendor = vendorTypeRepo.getTypeAndSpecByMask(parts[0]);
        if (aboutVendor==null) {
            throw new BusinessRuleException("contract.mask.not.found");
        }
        aboutVendor.setCriteria(criteriaService.getCriteriaByType(aboutVendor.getVendorType()));        
        return new ResponseHandler<>(200, "Encontrado", "Encontrado", aboutVendor).getResponse();
    }
    
}
