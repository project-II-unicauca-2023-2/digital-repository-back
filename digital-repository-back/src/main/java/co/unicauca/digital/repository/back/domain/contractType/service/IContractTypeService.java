package co.unicauca.digital.repository.back.domain.contractType.service;

import co.unicauca.digital.repository.back.domain.contractType.dto.request.ContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractType.dto.request.ContractTypeDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.contractType.dto.response.ContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractType.dto.response.ContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

/**
 * Interface that allows defining the business operations to be carried out on the ContractType entity.
 */
public interface IContractTypeService {
    
    /**
     * Service to get a ContractType by ID
     * 
     * @param id Id object to search entity
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractTypeDtoFindResponse> getById(final int id);

    /**
     * Service to get all contractTypes
     * 
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(final int pageNo, final int pageSize);

    /**
     * Service to create a ContractType
     * 
     * @param contractTypeDtoCreateRequest {@link ContractTypeDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractTypeDtoCreateResponse> createContractType(final ContractTypeDtoCreateRequest contractTypeDtoCreateRequest);


    /**
     * Service to update a ContractType
     * 
     * @param contractTypeDtoUpdateRequest {@link ContractTypeDtoUpdateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ContractTypeDtoCreateResponse> updateContractType(final ContractTypeDtoUpdateRequest contractTypeDtoUpdateRequest);


    /**
     * Service to delete a ContractType
     * 
     * @param id ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<Boolean> deleteContractType(final int id);
}