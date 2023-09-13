package co.unicauca.digital.repository.back.domain.modalityContractType.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the MODALITY CONTRACT TYPE table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModalityContractTypeDtoUpdateRequest {

    /** ModalityContractType id */
    @NotNull(message = "{ModalityContractType.ID.field.not.null}")
    @Positive(message = "{ModalityContractType.ID.field.positive}")
    private Integer id;

    /** ModalityContractType ModalityId */
    @NotNull(message = "{ModalityContractType.modalityId.field.not.null}")
    @Positive(message = "{ModalityContractType.modalityId.field.positive}")
    private Integer modalityId;

    /** ModalityContractType ContractTypeId */
    @NotNull(message = "{ModalityContractType.contractTypeId.field.not.null}")
    @Positive(message = "{ModalityContractType.contractTypeId.field.positive}")
    private Integer contractTypeId;

    /** ModalityContractType InternalNormativeId */
    @NotNull(message = "{ModalityContractType.internalNormativeId.field.not.null}")
    @Positive(message = "{ModalityContractType.internalNormativeId.field.positive}")
    private Integer internalNormativeId;

}
