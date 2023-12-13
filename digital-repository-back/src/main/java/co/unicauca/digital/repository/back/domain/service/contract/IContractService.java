package co.unicauca.digital.repository.back.domain.service.contract;

import java.util.List;

import co.unicauca.digital.repository.back.domain.dto.aboutContractType.aboutContractTypeDTO;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoAverageRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoIdRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoExpiredQualifiedResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractVendorDtoResponse;
import co.unicauca.digital.repository.back.domain.dto.vendor.response.VendorDtoAboutData;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

/**
 * Interface that allows defining the business operations to be carried out on
 * the Contract entity.
 */
public interface IContractService {

    /**
     * Service to get a contract by ID
     *
     * @param id ID Object to do the search
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    Response<ContractDtoFindResponse> getById(final int id);

    /**
     * Service to get all contracts
     *
     * @param pageNo   Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(int pageNo, int pageSize);

    /**
     * Service to save a contract
     *
     * @param ContractDtoCreateRequest {@link ContractDtoCreateRequest} Object with
     *                                 the information to be inserted, received in
     *                                 the body of the request to the service
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    Response<ContractDtoCreateResponse> createContract(final ContractDtoCreateRequest ContractDtoCreateRequest);

    /**
     * Service to update a contract
     *
     * @param ContractDtoUpdateRequest {@link ContractDtoUpdateRequest} Object with
     *                                 the information to be updated, received in
     *                                 the body of the request to the service
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    Response<ContractDtoCreateResponse> updateContract(final ContractDtoUpdateRequest ContractDtoUpdateRequest);

    /**
     * Service to delete a contract
     *
     * @param id ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    Response<Boolean> deleteContract(final int id);

    /**
     * Contract Existence Verification Service
     * 
     * @param prmMask - The contract mask used to verify its existence
     * @return {@link Response} - Response object for the service, containing
     */

    Response<Boolean> entityExistsByReference(ContractDtoIdRequest prmContractParams);

    /**
     * Existence evaluation for a contract based on reference
     * 
     * @param prmMask - The contract mask used to verify its existence
     * @return {@link Response} - Response object for the service, containing
     */

    Response<Boolean> ExistEvaluationByReference(ContractDtoIdRequest prmContractParams);

    /**
     * Selected data from contract and vendor
     * 
     * @param prmMask - The contract mask used to verify its existence
     * @return {@link Response} - Response object for the service, containing
     */

    Response<ContractVendorDtoResponse> DataContractVendorByMask(ContractDtoIdRequest prmContractParams);

    /**
     * Service to get all contracts sort by SigningDate time descending
     *
     * @param pageNo   Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getContractualFoldersSortBySigningDate(int pageNo, int pageSize);

    /**
     * Service to get all contracts sort by SigningDate time descending
     *
     * @param filter Filter for the contractual folder
     * @param search Word to search like
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getContractualFoldersByFilter(int pageNo, int pageSize, String filter,
            String search);

    

    /**
     * 
     * @param description
     * @param anio
     * @return average category
     */
    Response<List<ContractDtoAverageRequest>> getAverageContractByCategory(String description,int year);
    //Response<ContractDtoAverageRequest> getAverageContractByCategory(String description,int year);

    /**
     * @return List of contracts expired and qualified
     */
    Response<List<ContractDtoExpiredQualifiedResponse>> getExpiredQualifiedContract();

    List<String> getAboutContractForVendor(int idVendor);

    /**
     * @return true if contract is finalized, else return false
     */
    Response<Boolean> isContractFinalized(final ContractDtoIdRequest prmContractParams);
}
