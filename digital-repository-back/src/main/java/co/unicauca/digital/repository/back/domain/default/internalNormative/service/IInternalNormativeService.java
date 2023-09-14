package co.unicauca.digital.repository.back.domain.internalNormative.service;

import co.unicauca.digital.repository.back.domain.internalNormative.dto.request.InternalNormativeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.internalNormative.dto.request.InternalNormativeDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.internalNormative.dto.response.InternalNormativeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.internalNormative.dto.response.InternalNormativeDtoFindResponse;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

/**
 * Interface that allows defining the business operations to be carried out on the Internal Normative entity.
 */
public interface IInternalNormativeService {

    /**
     * Service to save a internal normative
     *
     *@param internalNormativeDtoCreateRequest {@link InternalNormativeDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     *@return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<InternalNormativeDtoCreateResponse> createInternalNormative(final InternalNormativeDtoCreateRequest internalNormativeDtoCreateRequest);

    /**
     * Service to get an internal normative by id
     *
     *@param id {@link Integer} ID Object to do the search
     *@return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<InternalNormativeDtoFindResponse> getById(final Integer id);

    /**
     * Service to get all records from internal normative table
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(int pageNo, int pageSize);

    /**
     * Service to update a internal normative
     *
     *@param internalNormativeDtoUpdateRequest {@link InternalNormativeDtoUpdateRequest} Object with the information to be updated, received in the body
     *@return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<InternalNormativeDtoCreateResponse> updateInternalNormative(final InternalNormativeDtoUpdateRequest internalNormativeDtoUpdateRequest);

    /**
     * Service to delete an internal normative by id
     *
     *@param id {@link Integer} ID Object with the information to be deleted
     *@return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<Boolean> deleteInternalNormative(final Integer id);

}
