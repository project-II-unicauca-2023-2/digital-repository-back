package co.unicauca.digital.repository.back.domain.contractualDocumentType.service;

import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.request.ContractualDocumentTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response.ContractualDocumentTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response.ContractualDocumentTypeDtoFindResponse;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

/**
 * Interface that allows defining the business operations to be carried out on the ContractualDocumentType entity.
 */
public interface IContractualDocumentTypeService {

    /**
     * Service to save a contractualDocumentType
     *
     * @param contractualDocumentTypeDtoCreateRequest {@link ContractualDocumentTypeDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractualDocumentTypeDtoCreateResponse> createContractualDocumentType(final ContractualDocumentTypeDtoCreateRequest contractualDocumentTypeDtoCreateRequest);

    /**
     * Service to get a contractualDocumentType by ID
     *
     * @param id ID Object to do the search
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractualDocumentTypeDtoFindResponse> getById(final int id);

    /**
     * Service to get all contractualDocumentTypes
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(int pageNo, int pageSize);


    /**
     * Service to delete a contractualDocumentTypes
     *
     * @param id ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<Boolean> deleteContractualDocumentTypes(final int id);

}
