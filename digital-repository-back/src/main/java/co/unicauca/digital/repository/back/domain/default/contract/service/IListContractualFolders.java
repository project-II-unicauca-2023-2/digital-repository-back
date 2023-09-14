package co.unicauca.digital.repository.back.domain.contract.service;

import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

import java.util.List;

/**
 * Interface that allows defining the business operations for the HE01 to be carried out on the Contract entity.
 */
public interface IListContractualFolders {

    /**
     * Service to get all contracts sort by SigningDate time descending
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getContractualFoldersSortBySigningDate(int pageNo, int pageSize);

    /**
     * Service to get all contracts sort by SigningDate time descending
     *
     * @param filter Filter for the contractual folder
     * @param search Word to search like
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getContractualFoldersByFilter(int pageNo, int pageSize, String filter, String search);
}
