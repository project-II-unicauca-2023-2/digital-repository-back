package co.unicauca.digital.repository.back.domain.dto.vendorType.response;
import lombok.*;
/**
 * Class that 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class vendorTypeDtoGetTypeSpecResponse {

    /**
     * vendor type
     */
    private String type;

    /**
     * vendor type specification
     */
    private String typeSpec;
    
}

