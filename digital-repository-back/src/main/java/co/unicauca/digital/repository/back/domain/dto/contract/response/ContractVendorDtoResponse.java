package co.unicauca.digital.repository.back.domain.dto.contract.response;

import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.Column;

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

    //Vendor
    private String name;

    /** Vendor identification number */
    private String identification;
}
