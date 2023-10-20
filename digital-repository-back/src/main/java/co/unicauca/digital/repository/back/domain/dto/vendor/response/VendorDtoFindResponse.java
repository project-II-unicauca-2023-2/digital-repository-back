package co.unicauca.digital.repository.back.domain.dto.vendor.response;

import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the output of
 * information from the VENDOR table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorDtoFindResponse {

    /** Vendor ID */
    private Integer id;

    /** Vendor name */
    private String name;

    /** Vendor identification number */
    private String identification;
}
