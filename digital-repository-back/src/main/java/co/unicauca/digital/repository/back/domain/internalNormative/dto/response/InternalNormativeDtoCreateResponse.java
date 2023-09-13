package co.unicauca.digital.repository.back.domain.internalNormative.dto.response;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the INTERNAL NORMATIVE table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InternalNormativeDtoCreateResponse {

    /** InternalNormative name */
    private String name;

    /** InternalNormative isInForce */
    private Boolean isInForce;

    /** InternalNormative initialTime */
    private LocalDateTime initialTime;

    /** InternalNormative finalTime */
    private LocalDateTime finalTime;

    /** InternalNormative create User */
    private String createUser;

    /** InternalNormative create time  */
    private LocalDateTime createTime;

}
