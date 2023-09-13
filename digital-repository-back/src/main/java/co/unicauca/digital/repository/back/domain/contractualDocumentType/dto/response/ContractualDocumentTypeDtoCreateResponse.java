package co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response;

import lombok.*;

import java.time.LocalDateTime;
/**
 * Class that defines an entity for the O/R mapping for the output of information from the ContractualDocumentType table.
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ContractualDocumentTypeDtoCreateResponse {

    /** ContractualDocumentType name */
    private String name;

    /** ContractualDocumentType description */
    private String description;

    /** ContractualDocumentType createTime */
    private LocalDateTime createTime;

    /** ContractualDocumentType createUser */
    private String createUser;


}
