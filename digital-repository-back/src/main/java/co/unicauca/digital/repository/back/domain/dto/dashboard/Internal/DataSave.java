package co.unicauca.digital.repository.back.domain.dto.dashboard.Internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataSave {
    private Integer id;
    private Integer contractTypeId;
    private String name;
}
