package co.unicauca.digital.repository.back.domain.dto.contract.response;

import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the output of
 * information from the CONTRACT table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDtoFindContractualFoldersResponse {

    /** Contract id */
    private Integer id;

    /** Contract code */
    private String reference;

    /** Contract Modality */
    private String modality;

    /** Contract ContractType */
    private String contractType;

    /** Contract Vendor */
    private String vendor;

    /** Contract year of signature */
    private int signingYear;
}
