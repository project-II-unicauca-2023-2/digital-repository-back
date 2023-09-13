package co.unicauca.digital.repository.back.domain.modality.dto.request;

import lombok.*;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the Modality table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModalityDtoUpdateRequest {

    /** Modality id */
    @NotNull(message = "{modality.ID.field.not.null}")
    @Positive(message = "{modality.ID.field.positive}")
    private Integer id;

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
 
     /*Modality updateUser */
     @NotNull(message = "{modality.updateUser.field.not.null}")
     @NotEmpty(message = "{modality.updateUser.field.not.empty}")
     private String updateUser;

}