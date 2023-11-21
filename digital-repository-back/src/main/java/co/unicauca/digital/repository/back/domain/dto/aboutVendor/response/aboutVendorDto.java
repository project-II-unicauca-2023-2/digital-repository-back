package co.unicauca.digital.repository.back.domain.dto.aboutVendor.response;

import java.util.List;

import co.unicauca.digital.repository.back.domain.dto.criteria.response.CriteriaDtoConsultResponse;
import co.unicauca.digital.repository.back.domain.model.criteria.Criteria;
import lombok.*;
/**
 * Class that 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class aboutVendorDto {

    /**
     * vendor type
     */
    private String vendorType;
    private Integer contractTypeId;
    private String contractName;
    private List<CriteriaDtoConsultResponse> criteria;

    
    public aboutVendorDto(Integer pId, String pType, String pTypeSpec){
        contractTypeId=pId;
        vendorType=pType;
        contractName=pTypeSpec;
    }    
}

