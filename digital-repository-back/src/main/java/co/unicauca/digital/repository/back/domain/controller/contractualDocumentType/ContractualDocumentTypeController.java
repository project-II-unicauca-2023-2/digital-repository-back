package co.unicauca.digital.repository.back.domain.controller.contractualDocumentType;

import co.unicauca.digital.repository.back.domain.dto.contractualDocumentType.request.ContractualDocumentTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.contractualDocumentType.response.ContractualDocumentTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.contractualDocumentType.response.ContractualDocumentTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.service.contractualDocumentType.IContractualDocumentTypeService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contractualDocumentType")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContractualDocumentTypeController {

    /** Object used to invoke the operations of the IContractServe interface */
    private final IContractualDocumentTypeService contractualDocumentTypeService;

    /**
     * constructor method
     */
    public ContractualDocumentTypeController(IContractualDocumentTypeService contractualDocumentTypeService) {
        this.contractualDocumentTypeService = contractualDocumentTypeService;
    }

    /**
     * API to create a ContractualDocumentType
     *
     * @param contractualDocumentTypeDtoCreateRequest {@link ContractualDocumentTypeDtoCreateRequest}
     *                                                Object with the information to
     *                                                be inserted, received in the
     *                                                body of the request to the
     *                                                service
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<ContractualDocumentTypeDtoCreateResponse>> createContract(
            @Valid @RequestBody final ContractualDocumentTypeDtoCreateRequest contractualDocumentTypeDtoCreateRequest) {
        return new ResponseEntity<>(this.contractualDocumentTypeService
                .createContractualDocumentType(contractualDocumentTypeDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to get a ContractualDocumentType by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<ContractualDocumentTypeDtoFindResponse>> getById(
            @Valid @PathVariable final Integer id) {
        return new ResponseEntity<>(this.contractualDocumentTypeService.getById(id), HttpStatus.OK);
    }

    /**
     * API to get all ContractualDocumentTypes
     *
     * @param pageNo   Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @GetMapping("")
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(this.contractualDocumentTypeService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to delete a ContractualDocumentType by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteContractualDocumentType(@Valid @PathVariable final Integer id) {
        return new ResponseEntity<>(this.contractualDocumentTypeService.deleteContractualDocumentTypes(id),
                HttpStatus.OK);
    }
}
