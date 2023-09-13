package co.unicauca.digital.repository.back.domain.User.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that defines an entity for the O/R mapping of the USER table.
 */
@Entity @Table(name = "user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    /** User id */
    @Id
    @Column(name = "identification_number", length = 30)
    private String identificationNumber;

    /** User full name */
    @Column(length = 70)
    private String fullName;
}
