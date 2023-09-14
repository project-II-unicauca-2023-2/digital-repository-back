package co.unicauca.digital.repository.back.domain.contractualDocument.rest;

import co.unicauca.digital.repository.back.domain.contractualDocument.dto.request.ContractualDocumentDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoFindResponse;
import co.unicauca.digital.repository.back.domain.contractualDocument.service.IContractualDocumentService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contractualDocument")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContractualDocumentController {

    /** Object used to invoke the operations of the IContractualDocumentService interface */
    private final IContractualDocumentService contractualDocumentService;

    /**
     * constructor method
     */
    public ContractualDocumentController(IContractualDocumentService contractualDocumentService) {
        this.contractualDocumentService = contractualDocumentService;
    }


    /**
     * API to create a ContractualDocument
     *
     * @param contractualDocumentDtoCreateRequest {@link ContractualDocumentDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<ContractualDocumentDtoCreateResponse>> createContractualDocument(@Valid @RequestBody final ContractualDocumentDtoCreateRequest contractualDocumentDtoCreateRequest){
        return new ResponseEntity<>(this.contractualDocumentService.createContractualDocumentType(contractualDocumentDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to get a ContractualDocument by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<ContractualDocumentDtoFindResponse>> getById(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.contractualDocumentService.getById(id), HttpStatus.OK);
    }

    /**
     * API to get all ContractualDocuments
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("")
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(this.contractualDocumentService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to delete a ContractualDocument by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteContractualDocument(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.contractualDocumentService.deleteContractualDocument(id), HttpStatus.OK);
    }
}
