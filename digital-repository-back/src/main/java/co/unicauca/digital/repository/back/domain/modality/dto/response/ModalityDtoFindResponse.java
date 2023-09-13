package co.unicauca.digital.repository.back.domain.modality.dto.response;
import java.time.LocalDateTime;

import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the output of information from the Modality table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModalityDtoFindResponse {

     /** Modality ID */
     private Integer id;

     /** Modality name */
     private String name;
 
     /** Modality externalCode */
     private String externalCode;

     /** Modality create User */
    private String createUser;

    /** Modality create time  */
    private LocalDateTime createTime;
}
