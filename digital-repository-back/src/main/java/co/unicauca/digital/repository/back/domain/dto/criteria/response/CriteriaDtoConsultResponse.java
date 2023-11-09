package co.unicauca.digital.repository.back.domain.dto.criteria.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CriteriaDtoConsultResponse {

    private String criteriaType;

    private String description;

    private String name;
}