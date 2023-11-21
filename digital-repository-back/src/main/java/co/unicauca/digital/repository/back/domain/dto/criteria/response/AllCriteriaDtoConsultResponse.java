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

public class AllCriteriaDtoConsultResponse {

    private String criteriaType;

    private String calidad;

    private String ejecucion;

    private String cumplimiento;

}