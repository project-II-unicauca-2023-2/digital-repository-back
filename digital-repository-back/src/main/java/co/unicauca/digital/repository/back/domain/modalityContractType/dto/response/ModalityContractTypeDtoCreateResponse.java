package co.unicauca.digital.repository.back.domain.modalityContractType.dto.response;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the MODALITY CONTRACT TYPE table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModalityContractTypeDtoCreateResponse {

    /** ModalityContractType ModalityId */
    private Integer modalityId;

    /** ModalityContractType ContractTypeId */
    private Integer contractTypeId;

    /** ModalityContractType InternalNormativeId */
    private Integer internalNormativeId;

    /** ModalityContractType create User */
    private String createUser;

    /** ModalityContractType create time  */
    private LocalDateTime createTime;
}
