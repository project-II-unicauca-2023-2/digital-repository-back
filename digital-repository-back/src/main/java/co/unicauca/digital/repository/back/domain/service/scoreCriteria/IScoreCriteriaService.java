package co.unicauca.digital.repository.back.domain.service.scoreCriteria;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDtoResponse;
import co.unicauca.digital.repository.back.global.response.Response;

public interface IScoreCriteriaService {
    /**
     *  Selected data from Criteria, ScoreCritera and Score
     *  @param prmMask - The contract mask used to verify its existence
     *  @return {@link Response} - Response object for the service, containing
     */
    Response<ScoreCriteriaDtoResponse> DataScoreCriteriaByMask(String prmMask);
}