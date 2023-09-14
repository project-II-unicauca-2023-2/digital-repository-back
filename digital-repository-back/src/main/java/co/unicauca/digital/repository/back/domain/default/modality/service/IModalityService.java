package co.unicauca.digital.repository.back.domain.modality.service;

import co.unicauca.digital.repository.back.domain.modality.dto.request.ModalityDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.modality.dto.request.ModalityDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.modality.dto.response.ModalityDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.modality.dto.response.ModalityDtoFindResponse;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

/**
 * Interface that allows defining the business operations to be carried out on the Modality entity.
 */
public interface IModalityService {
    
    /**
     * Service to get a modality by ID
     * 
     * @param id Id object to search entity
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ModalityDtoFindResponse> getById(final int id);

    /**
     * Service to get all modalitys
     * 
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(final int pageNo, final int pageSize);

    /**
     * Service to create a Modality
     * 
     * @param modalityDtoCreateRequest {@link modalityDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ModalityDtoCreateResponse> createModality(final ModalityDtoCreateRequest modalityDtoCreateRequest);


    /**
     * Service to update a Modality
     * 
     * @param modalityDtoUpdateRequest {@link modalityDtoUpdateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ModalityDtoCreateResponse> updateModality(final ModalityDtoUpdateRequest modalityDtoUpdateRequest);


    /**
     * Service to delete a Modality
     * 
     * @param id ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<Boolean> deleteModality(final int id);

}
