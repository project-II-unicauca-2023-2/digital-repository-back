package co.unicauca.digital.repository.back.domain.dto.scoreCriteria.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoIdRequest;
import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the input of information
 * from the ScoreCriteria table.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCriteriaDtoCreateRequest {

    /** Contract mask for rating*/
  
    private ContractDtoIdRequest prmContractParams;
    
    /** List of ratings and criteria for rating a contract */
    @NotNull(message = "{scoreCriteria.listCriteriaRate.field.not.null}")
    @NotEmpty(message = "{scoreCriteria.listCriteriaRate.field.not.empty}")
    List<ScoreCriteriaDtoCreate> listCriteriaRate;

}
