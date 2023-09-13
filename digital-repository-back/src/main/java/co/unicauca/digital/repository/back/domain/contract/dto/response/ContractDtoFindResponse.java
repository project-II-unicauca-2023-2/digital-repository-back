package co.unicauca.digital.repository.back.domain.contract.dto.response;

import co.unicauca.digital.repository.back.domain.collection.dto.response.CollectionDtoResponse;
import co.unicauca.digital.repository.back.domain.contract.model.ContractStatusEnum;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the CONTRACT table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContractDtoFindResponse {

    /** Contract id */
    private Integer id;

    /** Contract code */
    private String reference;

    /** Contract initial date */
    private LocalDateTime initialDate;

    /** Contract final date */
    private LocalDateTime finalDate;

    @Enumerated(EnumType.STRING)
    private ContractStatusEnum status;

    /** Contract vendor */
    private String vendor;

    /** Contract subject */
    private String subject;

    /** Contract modalityContractType */
    private Integer modalityContractType;

    /** */
    private List<CollectionDtoResponse> collections;
}
