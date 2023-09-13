package co.unicauca.digital.repository.back.domain.contractualDocument.dto.response;

import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response.ContractualDocumentTypeDtoFindResponse;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Class that defines an entity for the O/R mapping of the ContractualDocument table.
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ContractualDocumentDtoFindResponse {

    /** ContractualDocument id */
    private Integer id;

    /** ContractualDocument description */
    private String description;

    /** ContractualDocument isRequired */
    private Boolean isRequired;

    /** ContractualDocument subdirectory */
    private String subdirectory;

    /** ContractualDocument ordering */
    private Integer ordering;

    /** ContractualDocument createTime */
    private LocalDateTime createTime;

    /** ContractualDocument createUser */
    private String createUser;

    /** ContractualDocumentType ContractualDocumentType */
    private ContractualDocumentTypeDtoFindResponse contractualDocumentType;

    /** ContractualDocument ModalityContractType */
    private Integer modalityContractType;
}
