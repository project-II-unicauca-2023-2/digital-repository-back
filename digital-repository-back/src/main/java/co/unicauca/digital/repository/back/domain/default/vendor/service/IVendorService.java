package co.unicauca.digital.repository.back.domain.vendor.service;

import co.unicauca.digital.repository.back.domain.vendor.dto.request.VendorDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.vendor.dto.request.VendorDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.vendor.dto.response.VendorDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.vendor.dto.response.VendorDtoFindResponse;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

/**
 * Interface that allows defining the business operations to be carried out on the Vendor entity.
 */
public interface IVendorService {

    /**
     * Service to get a vendor by ID
     *
     * @param id ID Object to do the search
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<VendorDtoFindResponse> getById(final int id);

    /**
     * Service to get all vendors
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(int pageNo, int pageSize);

    /**
     * Service to save a vendor
     *
     * @param vendorDtoCreateRequest {@link VendorDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<VendorDtoCreateResponse> createVendor(final VendorDtoCreateRequest vendorDtoCreateRequest);

    /**
     * Service to update a vendor
     *
     * @param vendorDtoUpdateRequest {@link VendorDtoUpdateRequest} Object with the information to be updated, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<VendorDtoCreateResponse> updateVendor(final VendorDtoUpdateRequest vendorDtoUpdateRequest);

    /**
     * Service to delete a vendor
     *
     * @param id ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<Boolean> deleteVendor(final int id);
}
