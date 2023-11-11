package co.unicauca.digital.repository.back.domain.dto.criteria.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CriteriaDtoConsultResponse {

    private Integer id;
    private String name;
    private String description;
    
}