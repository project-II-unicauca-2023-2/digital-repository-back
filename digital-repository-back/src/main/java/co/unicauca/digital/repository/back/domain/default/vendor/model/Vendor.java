package co.unicauca.digital.repository.back.domain.vendor.model;

import co.unicauca.digital.repository.back.domain.contract.model.Contract;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class that defines an entity for the O/R mapping of the CONTRACT table.
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vendor {

    /** Vendor id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Vendor name */
    @Column(length = 250)
    private String name;

    /** Vendor identification number */
    @Column(length = 250, unique = true)
    private String identification;

    /** Vendor create User */
    @Column(length = 250)
    private String createUser;

    /** Vendor create time  */
    private LocalDateTime createTime;

    /** Vendor last update user */
    @Column(length = 250)
    private String updateUser;

    /** Vendor last update time */
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "vendor")
    private List<Contract> contracts;
}

