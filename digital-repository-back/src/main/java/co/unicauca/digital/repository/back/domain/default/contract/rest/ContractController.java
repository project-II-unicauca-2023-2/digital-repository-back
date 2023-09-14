package co.unicauca.digital.repository.back.domain.contract.rest;

import co.unicauca.digital.repository.back.domain.contract.dto.request.ContractDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contract.dto.request.ContractDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.contract.dto.response.ContractDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contract.dto.response.ContractDtoFindResponse;
import co.unicauca.digital.repository.back.domain.contract.service.IContractService;
import co.unicauca.digital.repository.back.domain.contract.service.IListContractualFolders;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contract")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContractController {

    /** Object used to invoke the operations of the IContractServe interface */
    private final IContractService contractService;

    /** Object used to invoke the operations of the HE-01 IListContractualFolders interface */
    private final IListContractualFolders listContractualFolders;

    /**
     * constructor method
     */
    public ContractController(IContractService contractService, IListContractualFolders listContractualFolders) {
        this.contractService = contractService;
        this.listContractualFolders = listContractualFolders;
    }

    /**
     * API to get a Contract by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<ContractDtoFindResponse>> getById(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.contractService.getById(id), HttpStatus.OK);
    }

    /**
     * API to get all Contracts
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("")
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(this.contractService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to create a Contract
     *
     * @param ContractDtoCreateRequest {@link ContractDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<ContractDtoCreateResponse>> createContract(@Valid @RequestBody final ContractDtoCreateRequest ContractDtoCreateRequest){
        return new ResponseEntity<>(this.contractService.createContract(ContractDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to update a Contract
     *
     * @param contractDtoUpdateRequest {@link ContractDtoUpdateRequest} Object with the information to be updated, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PatchMapping("")
    public ResponseEntity<Response<ContractDtoCreateResponse>> updateContract(@Valid @RequestBody final ContractDtoUpdateRequest contractDtoUpdateRequest){
        return new ResponseEntity<>(this.contractService.updateContract(contractDtoUpdateRequest), HttpStatus.OK);
    }

    /**
     * API to delete a Contract by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteContract(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.contractService.deleteContract(id), HttpStatus.OK);
    }

    /**
     * API to get all contracts sort by update time descending
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("contractualFolders")
    public ResponseEntity<Response<PageableResponse<Object>>> getContractualFoldersSortBySigningDate(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(this.listContractualFolders.getContractualFoldersSortBySigningDate(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to get all contracts sort by update time descending
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("contractualFoldersFilterPattern")
    public ResponseEntity<Response<PageableResponse<Object>>> getContractualFoldersByFilter(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam() String filter,
            @RequestParam() String search){
        return new ResponseEntity<>(this.listContractualFolders.getContractualFoldersByFilter(pageNo, pageSize, filter, search), HttpStatus.OK);
    }
}
