package co.unicauca.digital.repository.back.domain.dto.contract.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the CONTRACT table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDtoIdRequest {

    /** Contract mask */
    @NotNull(message = "{contract.subject.field.not.null}")
    @NotEmpty(message = "{contract.subject.field.not.empty}")
    private String mascara;

    /** Contract anio */
    @NotNull(message = "{contract.subject.field.not.null}")
    @NotEmpty(message = "{contract.subject.field.not.empty}")
    private String anio;

    
}
