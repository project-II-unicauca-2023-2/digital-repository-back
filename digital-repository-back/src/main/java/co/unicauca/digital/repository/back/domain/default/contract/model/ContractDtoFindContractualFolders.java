package co.unicauca.digital.repository.back.domain.contract.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class that defines an entity for the O/R mapping for the output of information from the CONTRACT table.
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContractDtoFindContractualFolders {

    /** Contract id */
    @Id
    private Integer id;

    /** Contract code */
    private String reference;

    /** Contract Modality */
    private String modality;

    /** Contract ContractType */
    private String contractType;

    /** Contract Vendor  */
    private String vendor;

    /** Contract year of signature  */
    private int signingYear;
}
