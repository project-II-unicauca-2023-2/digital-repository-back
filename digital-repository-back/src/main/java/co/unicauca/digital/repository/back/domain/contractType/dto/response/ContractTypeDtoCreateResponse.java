package co.unicauca.digital.repository.back.domain.contractType.dto.response;

import lombok.*;

import java.time.LocalDateTime;


/**
 * Class that defines an entity for the O/R mapping for the output of information from the ContractType table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContractTypeDtoCreateResponse {
    /** ContractType name */
    private String name;

    /** ContractType externalCode */
    private String externalCode;

    /** ContractType create User */
    private String createUser;

    /** ContractType create time  */
    private LocalDateTime createTime;
}