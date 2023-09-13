package co.unicauca.digital.repository.back.domain.document.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDtoRequest {
    private Integer id;
    @NotNull(message = "{reference.field.not.null}")
    @NotEmpty(message = "{reference.field.not.empty}")
    private String name;
    @NotNull(message = "{reference.field.not.null}")
    @NotEmpty(message = "{reference.field.not.empty}")
    private String description;
    @NotNull(message = "{reference.field.not.null}")
    private Integer ordering;
    @NotNull(message = "{reference.field.not.null}")
    private String url;
    @NotNull(message = "{reference.field.not.null}")
    private Integer consecutive;
    @NotNull(message = "{reference.field.not.null}")
    private boolean isException;
    @NotNull(message = "{reference.field.not.null}")
    @NotEmpty(message = "{reference.field.not.empty}")
    private String type;
    @NotNull(message = "{reference.field.not.null}")
    private LocalDateTime expeditionDate;
    @NotNull(message = "{reference.field.not.null}")
    @NotEmpty(message = "{reference.field.not.empty}")
    private String createUser;
    @NotNull(message = "{reference.field.not.null}")
    private Integer contractId;
    @NotNull(message = "{reference.field.not.null}")
    private Integer contractualDocumentId;
}
