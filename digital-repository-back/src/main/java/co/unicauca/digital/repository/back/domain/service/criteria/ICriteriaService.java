package co.unicauca.digital.repository.back.domain.service.criteria;

import co.unicauca.digital.repository.back.domain.dto.criteria.request.CriteriaDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.criteria.response.CriteriaDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.modality.request.ModalityDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.modality.response.ModalityDtoCreateResponse;
import co.unicauca.digital.repository.back.global.response.Response;

public interface ICriteriaService {
    
    /**
     * Service to get a criteria by ID
     * 
     * @param id Id object to search entity
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    Response<CriteriaDtoCreateResponse> getById(final int id);


}
