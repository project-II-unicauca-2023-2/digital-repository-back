package co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ContractualDocumentTypeDtoFindResponse {

    /** ContractualDocumentType id */
    private Integer id;

    /** ContractualDocumentType name */
    private String name;

    /** ContractualDocumentType description */
    private String description;

    /** ContractualDocumentType createTime */
    private LocalDateTime createTime;

    /** ContractualDocumentType createUser */
    private String createUser;

}
