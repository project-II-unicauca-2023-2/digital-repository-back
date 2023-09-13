package co.unicauca.digital.repository.back.domain.modalityContractType.service;

import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoFindResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.request.ModalityContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.request.ModalityContractTypeDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.response.ModalityContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.response.ModalityContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

import java.util.List;


/**
 * Interface that allows defining the business operations to be carried out on the ModalityContractType entity.
 */
public interface IModalityContractTypeService {

    /**
     * Service to save a Modality Contract Type
     *
     * @param modalityContractTypeDtoCreateRequest {@link ModalityContractTypeDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ModalityContractTypeDtoCreateResponse> createModalityContractType(final ModalityContractTypeDtoCreateRequest modalityContractTypeDtoCreateRequest);

    /**
     * Service to get a Modality Contract Type for id
     *
     * @param id {@link Integer} ID Object to do the search
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ModalityContractTypeDtoFindResponse> getById(final Integer id);

    /**
     * Service to get all records from Modality Contract Type table
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<PageableResponse<Object>> getAll(int pageNo, int pageSize);

    /**
     * Service to get a Modality Contract Type for id
     *
     * @param contractTypeId {@link Integer} ID contractType to do the search
     * @param modalityId {@link Integer} ID modality to do the search
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ModalityContractTypeDtoFindResponse> getByContractModality(final Integer contractTypeId, final Integer modalityId);

    /**
     * Service to update a Modality Contract Type
     *
     * @param modalityContractTypeDtoUpdateRequest {@link ModalityContractTypeDtoUpdateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<ModalityContractTypeDtoCreateResponse> updateModalityContractType(final ModalityContractTypeDtoUpdateRequest modalityContractTypeDtoUpdateRequest);

    /**
     * Service to delete a Modality Contract Type for id
     *
     * @param id {@link Integer} ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<Boolean> deleteModalityContractType(final Integer id);

    /**
     * Service to find All a ContractualDocument by id
     *
     * @param id {@link Integer} ID Object with the information to be deleted
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    Response<List<ContractualDocumentDtoFindResponse>> getCheckListById(final Integer id);


}
