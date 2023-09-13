package co.unicauca.digital.repository.back.domain.collection.dto.request;

import co.unicauca.digital.repository.back.domain.document.dto.request.DocumentDtoRequest;
import co.unicauca.digital.repository.back.domain.document.model.Document;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionDtoRequest {

    private Integer id;
    @NotNull(message = "{reference.field.not.null}")
    private Integer contractId;
    private boolean isLocalRequerid;
    @NotNull(message = "{reference.field.not.null}")
    @NotEmpty(message = "{reference.field.not.empty}")
    private String createUser;
    private List<DocumentDtoRequest> documents;

//    @NotNull(message = "{reference.field.not.null}")
//    private ContractualDocument contractualDocument;
//
//    @NotNull(message = "{reference.field.not.null}")
//    private Contract contract;

}
