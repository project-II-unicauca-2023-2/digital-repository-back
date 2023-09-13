package co.unicauca.digital.repository.back.domain.internalNormative.model;

import co.unicauca.digital.repository.back.domain.modalityContractType.model.ModalityContractType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class that defines an entity for the O/R mapping of the INTERNAL NORMATIVE table.
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InternalNormative {

    /** InternalNormative id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** InternalNormative name */
    @Column(length = 250)
    private String name;

    /** InternalNormative isInForce */
    @Column(nullable = false)
    private Boolean isInForce;

    /** InternalNormative initialTime */
    @Column(nullable = false)
    private LocalDateTime initialTime;

    /** InternalNormative finalTime */
    private LocalDateTime finalTime;

    /** InternalNormative create User */
    @Column(length = 250)
    private String createUser;

    /** InternalNormative create time  */
    private LocalDateTime createTime;

    /** InternalNormative last update user */
    @Column(length = 250)
    private String updateUser;

    /** InternalNormative last update time */
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "internalNormative", cascade = CascadeType.ALL)
    private List<ModalityContractType> modalityContractType;
}
