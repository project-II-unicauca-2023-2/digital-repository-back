package co.unicauca.digital.repository.back.domain.contractualDocument.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Class that defines an entity for the O/R mapping for the input of information from the ContractualDocument table.
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ContractualDocumentDtoCreateRequest {


    /** ContractualDocumentType description */
    @NotNull(message = "{contractualDocumentRequest.description.field.mull}")
    @NotEmpty(message = "{contractualDocumentRequest.description.field.empty}")
    private String description;

    /** ContractualDocumentType isRequired */
    @NotNull(message = "{contractualDocumentRequest.isRequired.field.null}")
    private Boolean isRequired;

    /** ContractualDocumentType subdirectory */
    @NotNull(message = "{contractualDocumentRequest.subdirectory.field.null}")
    @NotEmpty(message = "{contractualDocumentRequest.subdirectory.field.empty}")
    private String subdirectory;

    /** ContractualDocumentType ordering */
    @NotNull(message = "{contractualDocumentRequest.ordering.field.null}")
    private Integer ordering;

    /** ContractualDocumentType createUser */
    @NotNull(message = "{contractualDocumentRequest.createUser.field.null}")
    @NotEmpty(message = "{contractualDocumentRequest.createUser.field.empty}")
    private String createUser;

    /** ContractualDocumentType ContractualDocumentType */
    @NotNull(message = "{contractualDocumentRequest.contractualDocumentType.field.null}")
    private Integer contractualDocumentType;

    /** ContractualDocument ModalityContractType */
    @NotNull(message = "{contractualDocumentRequest.modalityContractType.field.null}")
    private Integer modalityContractType;

}
