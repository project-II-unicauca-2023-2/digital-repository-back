package co.unicauca.digital.repository.back.domain.modality.dto.response;

import lombok.*;

import java.time.LocalDateTime;


/**
 * Class that defines an entity for the O/R mapping for the output of information from the Modality table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ModalityDtoCreateResponse {
    /** Modality name */
    private String name;

    /** Modality externalCode */
    private String externalCode;

    /** Modality create User */
    private String createUser;

    /** Modality create time  */
    private LocalDateTime createTime;

}
