package co.unicauca.digital.repository.back.domain.vendor.dto.response;
import lombok.*;

import java.time.LocalDateTime;


/**
 * Class that defines an entity for the O/R mapping for the output of information from the VENDOR table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VendorDtoCreateResponse {
    /** Vendor name */
    private String name;

    /** Vendor identification number */
    private String identification;

    /** Vendor create User */
    private String createUser;

    /** Vendor create time  */
    private LocalDateTime createTime;
}
