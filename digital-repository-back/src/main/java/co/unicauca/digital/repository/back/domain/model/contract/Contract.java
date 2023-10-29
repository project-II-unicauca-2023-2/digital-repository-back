package co.unicauca.digital.repository.back.domain.model.contract;

import co.unicauca.digital.repository.back.domain.model.collection.Collection;
import co.unicauca.digital.repository.back.domain.model.modalityContractType.ModalityContractType;
import co.unicauca.digital.repository.back.domain.model.score.Score;
import co.unicauca.digital.repository.back.domain.model.vendor.Vendor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class that defines an entity for the O/R mapping of the CONTRACT table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    /** Contract id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Contract code */
    @Column(length = 250, unique = true)
    private String reference;

    /** Contract date of signature */
    private LocalDateTime signingDate;

    /** Contract initial date */
    private LocalDateTime initialDate;

    /** Contract final date */
    private LocalDateTime finalDate;

    @Enumerated(EnumType.STRING)
    private ContractStatusEnum status;

    /** Contract subject */
    @Column(length = 250)
    private String subject;

    /** Contract create User */
    @Column(length = 250)
    private String createUser;

    /** Contract create time */
    private LocalDateTime createTime;

    /** Contract last update user */
    @Column(length = 250)
    private String updateUser;

    /** Contract last update time */
    private LocalDateTime updateTime;

    /** Contract Vendor */
    @ManyToOne(optional = false)
    @JoinColumn(name = "vendorId")
    private Vendor vendor;

    /** Contract Collections */
    @OneToMany(mappedBy = "contract")
    private List<Collection> collections;

    /** Contract ModalityContractType */
    @ManyToOne(optional = false)
    @JoinColumn(name = "modalityContractTypeId")
    private ModalityContractType modalityContractType;

    /**Aqui se utilizo una relacion one to one con la estrategia de clave compartida, la clave primaria de contrato es la misma clave primaria
     * de score, de esta manera funciona como primeria y foranea al tiempo.
     */
    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Score score;
}
