package co.unicauca.digital.repository.back.domain.dto.contract.response;

import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that defines an entity for the O/R mapping for the output of
 * information from the CONTRACT table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractVendorDtoResponse {

    /** Contract initial date */
    private LocalDateTime initialDate;

    /** Contract final date */
    private LocalDateTime finalDate;

    /** Contract subject */
    private String subject;

    /** Vendor name */
    private String name;

    /** Vendor identification number */
    private String identification;

    /** If isNaturalPerson is false, then the personType is juridical.*/
    @JsonProperty("isNaturalPerson")
    private boolean isNaturalPerson;
}
