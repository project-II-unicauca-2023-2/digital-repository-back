package co.unicauca.digital.repository.back.domain.collection.service;

import co.unicauca.digital.repository.back.domain.collection.dto.request.CollectionDtoRequest;
import co.unicauca.digital.repository.back.domain.collection.dto.response.CollectionDtoResponse;
import co.unicauca.digital.repository.back.domain.contract.model.Contract;
import co.unicauca.digital.repository.back.domain.contractualDocument.model.ContractualDocument;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

import java.util.List;

/**
 * Interface that allows defining the business operations to be carried out on the Collection entity.
 */
public interface ICollectionService {
    /**
     * Service to create a collection
     *
     * @param collectionDtoRequest Object with the information to be created
     * @return Response object for the service, containing information about the transaction outcome.
     */
    Response<CollectionDtoResponse> createCollection(final CollectionDtoRequest collectionDtoRequest);

    /**
     * Service to get a collection by ID
     *
     * @param id ID of the collection to retrieve
     * @return Response object for the service, containing information about the transaction outcome.
     */
    Response<CollectionDtoResponse> getByIdCollection(Integer id);

    /**
     * Service to get all collections
     *
     * @param pageNumber Pagination page number
     * @param pageSize Pagination page size
     * @return Response object for the service, containing information about the transaction outcome.
     */
    Response<PageableResponse<Object>> getAll(int pageNumber, int pageSize);

    /**
     * Service to update a collection
     *
     * @param collectionDtoRequest Object with the information to be updated
     * @return Response object for the service, containing information about the transaction outcome.
     */
    Response<CollectionDtoResponse> update(final CollectionDtoRequest collectionDtoRequest);

    /**
     * Service to delete a collection by ID
     *
     * @param id ID of the collection to delete
     * @return Response object for the service, indicating whether the deletion was successful.
     */
    Response<Boolean> delete(Integer id);

    /**
     *
     * @param contract
     * @return
     */
    Boolean createCollections(Contract contract);

    /**
     *
     * @param collectionDtoRequests
     * @return
     */
    Response<List<CollectionDtoResponse>> saveDocuments(List<CollectionDtoRequest> collectionDtoRequests);

    Response<CollectionDtoResponse> getCollectionIdByContractualdocumentAndContract(Integer contractualDocumentId, Integer contractId);


}
