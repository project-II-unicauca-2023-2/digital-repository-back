package co.unicauca.digital.repository.back.domain.dto.contract.response;

import co.unicauca.digital.repository.back.domain.model.contract.ContractStatusEnum;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the CONTRACT table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDtoExpiredQualifiedResponse {

    /** contract ID */
    private Integer id;
    
    /** Contract code */
    private String reference;

    private Integer vendorId;

    private String contractType;

    private String modality;

    private Integer initialYear;

    private LocalDateTime initialDate;

    private LocalDateTime finalDate;

    private float scoreTotal;
}
