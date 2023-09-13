package co.unicauca.digital.repository.back.domain.contract.service;

import co.unicauca.digital.repository.back.domain.collection.model.Collection;
import co.unicauca.digital.repository.back.domain.contract.dto.request.ContractDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contract.dto.request.ContractDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.contract.dto.response.ContractDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contract.dto.response.ContractDtoFindResponse;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

import java.util.List;

/**
 * Interface that allows defining the business operations to be carried out on the Contract entity.
 */
public interface IContractService {

    /**
     * Service to get a contract by ID
     *
     * @param id ID Object to do the search
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractDtoFindResponse> getById(final int id);

    /**
     * Service to get all contracts
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(int pageNo, int pageSize);

    /**
     * Service to save a contract
     *
     * @param ContractDtoCreateRequest {@link ContractDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractDtoCreateResponse> createContract(final ContractDtoCreateRequest ContractDtoCreateRequest);

    /**
     * Service to update a contract
     *
     * @param ContractDtoUpdateRequest {@link ContractDtoUpdateRequest} Object with the information to be updated, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractDtoCreateResponse> updateContract(final ContractDtoUpdateRequest ContractDtoUpdateRequest);

    /**
     * Service to delete a contract
     *
     * @param id ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<Boolean> deleteContract(final int id);

}
