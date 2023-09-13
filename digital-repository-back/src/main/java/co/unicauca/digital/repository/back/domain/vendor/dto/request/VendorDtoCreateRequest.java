package co.unicauca.digital.repository.back.domain.vendor.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class that defines an entity for the O/R mapping for the input of information from the Vendor table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VendorDtoCreateRequest {

    /** Vendor name */
    @NotNull(message = "{vendor.name.field.not.null}")
    @NotEmpty(message = "{vendor.name.field.not.empty}")
    private String name;

    /** Vendor identification number */
    @NotNull(message = "{vendor.id.field.not.null}")
    @NotEmpty(message = "{vendor.id.field.not.empty}")
    private String identification;

}
