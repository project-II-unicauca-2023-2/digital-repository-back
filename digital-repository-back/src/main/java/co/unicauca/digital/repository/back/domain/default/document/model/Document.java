package co.unicauca.digital.repository.back.domain.document.model;
import co.unicauca.digital.repository.back.domain.collection.model.Collection;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    /** Document id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Document name */
    @Column(length = 250)
    private String name;

    /** Document description */
    private String description;

    /** Document Ordering */
    private Integer ordering;

    /** Document url */
    @Column(length = 250)
    private String url;

    /** Document Consecutive */
    private Integer consecutive;

    /** Document isException */
    private boolean isException;

    /** Document type */
    @Column(length = 250)
    private String type;

    /** Document expeditionDate */
    private LocalDateTime expeditionDate;

    /** Document create user */
    @Column(length = 250)
    private String createUser;

    /** Document create time */
    private LocalDateTime createTime;

    /** Document update user */
    @Column(length = 250)
    private String updateUser;

    /** Document update time */
    private LocalDateTime updateTime;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "collectionId")
    private Collection collection;


}