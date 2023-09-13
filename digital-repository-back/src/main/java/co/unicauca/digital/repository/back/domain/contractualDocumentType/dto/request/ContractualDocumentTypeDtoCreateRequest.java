package co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the ContractualDocumentType table.
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ContractualDocumentTypeDtoCreateRequest {

    /** ContractualDocumentType name */
    @NotNull(message = "{contractualDocumentTypeRequest.name.field.mull}")
    @NotEmpty(message = "{contractualDocumentTypeRequest.name.field.empty}")
    private String name;

    /** ContractualDocumentType description */
    @NotNull(message = "{contractualDocumentTypeRequest.description.field.mull}")
    @NotEmpty(message = "{contractualDocumentTypeRequest.description.field.empty}")
    private String description;

    /** ContractualDocumentType createUser */
    @NotNull(message = "{contractualDocumentTypeRequest.createUser.field.null}")
    @NotEmpty(message = "{contractualDocumentTypeRequest.createUser.field.empty}")
    private String createUser;

}
