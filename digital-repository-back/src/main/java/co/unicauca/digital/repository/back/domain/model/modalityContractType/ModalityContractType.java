package co.unicauca.digital.repository.back.domain.model.modalityContractType;

import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;
import co.unicauca.digital.repository.back.domain.model.contractualDocument.ContractualDocument;
import co.unicauca.digital.repository.back.domain.model.internalNormative.InternalNormative;
import co.unicauca.digital.repository.back.domain.model.modality.Modality;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class that defines an entity for the O/R mapping of the MODALITY CONTRACT
 * TYPE table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModalityContractType {

    /** ModalityContractType id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // TODO add relationships

    @ManyToOne(optional = false)
    @JoinColumn(name = "modalityId")
    private Modality modality;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contractTypeId")
    private ContractType contractType;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "internalNormativeId")
    private InternalNormative internalNormative;

    @Column(length = 250)
    private String externalCode;

    private Integer version;

    private Boolean isDisabled;

    /*
     * ModalityContractType InternalNormativeId
     * // private InternalNormative internalNormativeId;
     */

    /** ModalityContractType create User */
    @Column(length = 250)
    private String createUser;

    /** ModalityContractType create time */
    private LocalDateTime createTime;

    /** ModalityContractType last update user */
    @Column(length = 250)
    private String updateUser;

    /** ModalityContractType last update time */
    private LocalDateTime updateTime;

    /** ModalityContractType relation with ContractualDocument */

    @OneToMany(mappedBy = "modalityContractType")
    private List<ContractualDocument> contractualDocuments;

}
