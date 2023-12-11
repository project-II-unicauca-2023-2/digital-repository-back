package co.unicauca.digital.repository.back.domain.dto.aboutContract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class aboutContractIdDTO {
    private Integer idContrato;
    private String mask; 
    private Integer year; 
}
