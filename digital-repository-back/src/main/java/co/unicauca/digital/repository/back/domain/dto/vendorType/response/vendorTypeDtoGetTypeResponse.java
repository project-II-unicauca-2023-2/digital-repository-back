package co.unicauca.digital.repository.back.domain.dto.vendorType.response;
import java.util.List;

import lombok.*;
/**
 * Class that 
 */
public class vendorTypeDtoGetTypeResponse {

    /**
     * Vendor type
     */
    private String type;

    public vendorTypeDtoGetTypeResponse(){

    }
    public vendorTypeDtoGetTypeResponse(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    

    
    
}
