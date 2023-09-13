package co.unicauca.digital.repository.back.domain.modality.dto.request;

import lombok.*;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Class that defines an entity for the O/R mapping for the input of information from the Modality table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModalityDtoCreateRequest {
    
    /*Modality name */
    @NotNull(message = "{modality.name.field.not.null}")
    @NotEmpty(message = "{modality.name.field.not.empty}")
    private String name;

    /*Modality external code */
    @NotNull(message = "{modality.externalCode.field.not.null}")
    @NotEmpty(message = "{modality.externalCode.field.not.empty}")
    private String externalCode;

    /*Modality description */
    private String description;

    /*Modality createTime */
    private LocalDateTime createTime;

    /*Modality createUser */
    @NotNull(message = "{modality.createUser.field.not.null}")
    @NotEmpty(message = "{modality.createUser.field.not.empty}")
    private String createUser;

    /*Modality updateTime */
    private LocalDateTime updateTime;

    /*Modality updateUser */
    @NotNull(message = "{modality.updateUser.field.not.null}")
    @NotEmpty(message = "{modality.updateUser.field.not.empty}")
    private String updateUser;
}
