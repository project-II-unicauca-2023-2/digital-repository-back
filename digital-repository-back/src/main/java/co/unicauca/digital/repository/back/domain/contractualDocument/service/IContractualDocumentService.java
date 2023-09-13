package co.unicauca.digital.repository.back.domain.contractualDocument.service;

import co.unicauca.digital.repository.back.domain.contractualDocument.dto.request.ContractualDocumentDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoFindResponse;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

/**
 * Interface that allows defining the business operations to be carried out on the ContractualDocument entity.
 */
public interface IContractualDocumentService {

    /**
     * Service to save a contractualDocument
     *
     * @param contractualDocumentDtoCreateRequest {@link ContractualDocumentDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractualDocumentDtoCreateResponse> createContractualDocumentType(final ContractualDocumentDtoCreateRequest contractualDocumentDtoCreateRequest);

    /**
     * Service to get a contractualDocument by ID
     *
     * @param id ID Object to do the search
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractualDocumentDtoFindResponse> getById(final int id);

    /**
     * Service to get all contractualDocuments
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(int pageNo, int pageSize);


    /**
     * Service to delete a contractualDocument
     *
     * @param id ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<Boolean> deleteContractualDocument(final int id);


}
