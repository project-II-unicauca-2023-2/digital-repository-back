package co.unicauca.digital.repository.back.domain.User.model;

import lombok.*;

import javax.persistence.*;

/**
 * Class that defines an entity for the O/R mapping of the CREDENTIAL table.
 */
@Entity
@Table(name = "credential")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Credential {

    /** User id */
    @Id
    private Long identificationNumber;

    /** User password */
    @Column(length = 30)
    private String password;

    /** User full name */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identification_number", nullable = false)
    @MapsId
    private User user;
}
