package co.unicauca.digital.repository.back.domain.modalityContractType.dto.response;

import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the MODALITY CONTRACT TYPE table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModalityContractTypeDtoFindResponse {
    /** ModalityContractType id */
    private Integer id;

    /** ModalityContractType ModalityId */
    private Integer modalityId;

    /** ModalityContractType ContractTypeId */
    private Integer contractTypeId;

    /** ModalityContractType InternalNormativeId */
    private Integer internalNormativeId;
}
