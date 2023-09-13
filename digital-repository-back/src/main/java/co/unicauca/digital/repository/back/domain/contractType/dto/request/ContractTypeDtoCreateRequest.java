package co.unicauca.digital.repository.back.domain.contractType.dto.request;

import lombok.*;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the ContractType table.
 */

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContractTypeDtoCreateRequest {
    
    /*ContactType name */
    @NotNull(message = "{contractType.name.field.not.null}")
    @NotEmpty(message = "{contractType.name.field.not.empty}")
    private String name;

    /*ContactType external code */
    private String externalCode;

    /*ContactType description */
    private String description;

    /*ContactType createTime */
    private LocalDateTime createTime;
    
    /*ContactType create user */
    @NotNull(message = "{contractType.createUser.field.not.null}")
    @NotEmpty(message = "{contractType.createUser.field.not.empty}")
    private String createUser;

    /*ContactType updateTime */
    private LocalDateTime updateTime;

    /*ContactType update user */
    @NotNull(message = "{contractType.updateUser.field.not.null}")
    @NotEmpty(message = "{contractType.updateUser.field.not.empty}")
    private String updateUser;
}