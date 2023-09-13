package co.unicauca.digital.repository.back.domain.document.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDtoResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer ordering;
    private String url;
    private Integer consecutive;
    private boolean isException;
    private String type;
    private LocalDateTime expeditionDate;
    private String createUser;
    private LocalDateTime createTime;
    private String updateUser;
    private LocalDateTime updateTime;
    private Integer collectionId;
}
