package co.unicauca.digital.repository.back.domain.modality.model;

import co.unicauca.digital.repository.back.domain.modalityContractType.model.ModalityContractType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


/**
 * This class defines the entity for th O/R mapping of the Modality table
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Modality {

    /*Modality ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*Modality external code */
    @Column(length = 250, unique = true)
    private String externalCode;

    /*Modality description */
    private String description;

    /*Modality name */
    @Column(length = 250)
    private String name;

    /*Modality create time */
    private LocalDateTime createTime;

    /*Modality create user */
    @Column(length = 250)
    private String createUser;

    /*Modality update time */
    private LocalDateTime updateTime;

    /*Modality update user */
    @Column(length = 250)
    private String updateUser;

    @OneToMany(mappedBy = "modality", fetch = FetchType.LAZY)
    private List<ModalityContractType> modalityContractTypes;
}