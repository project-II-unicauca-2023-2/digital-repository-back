package co.unicauca.digital.repository.back.domain.contractualDocument.model;

import co.unicauca.digital.repository.back.domain.collection.model.Collection;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.model.ContractualDocumentType;
import co.unicauca.digital.repository.back.domain.document.model.Document;
import co.unicauca.digital.repository.back.domain.modalityContractType.model.ModalityContractType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contractualDocument")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ContractualDocument {

    /** ContractualDocument id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** ContractualDocument description */
    @Column(length = 700, name = "Description")
    private String description;

    /** ContractualDocument isRequired */
    private Boolean isRequired;

    /** ContractualDocument subdirectory */
    @Column(name = "Subdirectory")
    private Integer subdirectory;

    /** ContractualDocument subtype */
    @Column(name = "subtype")
    private Integer subtype;

    /** ContractualDocument ordering */
    @Column(name = "Ordering")
    private Integer ordering;

    /** ContractualDocument createTime */
    @Column(name = "CreateTime")
    private LocalDateTime createTime;

    /** ContractualDocument createUser */
    @Column(length = 250, name = "CreateUser")
    private String createUser;

    /** ContractualDocument updateTime */
    @Column(name = "UpdateTime")
    private LocalDateTime updateTime;

    /** ContractualDocument updateUser */
    @Column(length = 250, name = "UpdateUser")
    private String updateUser;

    /** ContractualDocument ContractualDocumentType */
    @ManyToOne()
    @JoinColumn(name = "contractualDocumentType")
    private ContractualDocumentType contractualDocumentType;

    /** ContractualDocument ModalityContractType */
    @ManyToOne()
    @JoinColumn(name = "modalityContractType")
    private ModalityContractType modalityContractType;

    @OneToMany(mappedBy = "contractualDocument",cascade = CascadeType.ALL)
    private List<Collection> collection;

}
