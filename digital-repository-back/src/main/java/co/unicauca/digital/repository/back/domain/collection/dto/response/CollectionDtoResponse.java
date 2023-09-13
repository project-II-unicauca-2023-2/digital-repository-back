package co.unicauca.digital.repository.back.domain.collection.dto.response;

import co.unicauca.digital.repository.back.domain.document.dto.response.DocumentDtoResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionDtoResponse {
    private Integer id;
    private boolean isLocalRequerid;
    private String createUser;
    private LocalDateTime createTime;
    private String updateUser;
    private LocalDateTime updateTime;
    private List<DocumentDtoResponse> documents;
    private Integer contractId;
    private Integer contractualDocumentId;

}
