package co.unicauca.digital.repository.back.domain.service.scoreCriteria;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoIdRequest;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.request.ScoreCriteriaDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaCalificationDomainDtoResponse;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDtoResponse;
import co.unicauca.digital.repository.back.global.response.Response;

public interface IScoreCriteriaService {
    /**
     *  Selected data from Criteria, ScoreCritera and Score
     *  @param prmMask - The contract mask used to verify its existence
     *  @return {@link Response} - Response object for the service, containing
     */
    Response<ScoreCriteriaDtoResponse> DataScoreCriteriaByMask(ContractDtoIdRequest prmContractParams);

    /**
     *  Get Calification Domain List
     *  @return {@link Response} - Response object for the service, containing
     */
    Response<ScoreCriteriaCalificationDomainDtoResponse> CalificationDomain();

    /**
     * Registers a list of califications for the specified domain.
     *
     * @param calificationRequest The request object containing calification information.
     * @return A {@link Response} object indicating the success or failure of the calification registration.
     */
    Response<Boolean> RegisterCalification(ScoreCriteriaDtoCreateRequest calificationRequest);

}