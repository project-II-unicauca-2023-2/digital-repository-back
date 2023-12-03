package co.unicauca.digital.repository.back.domain.controller.contract;

import co.unicauca.digital.repository.back.domain.dto.aboutContractType.aboutContractTypeDTO;
import co.unicauca.digital.repository.back.domain.dto.aboutVendor.response.aboutVendorDto;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoAverageRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoIdRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractVendorDtoResponse;
import co.unicauca.digital.repository.back.domain.service.contract.IContractService;
//import co.unicauca.digital.repository.back.domain.service.contract.IListContractualFolders;
import co.unicauca.digital.repository.back.domain.service.vendorType.IAboutVendorService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contract")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContractController {
    /**
     * Object used to invoke the operations of the IAboutVendor interface
     */
    @Autowired
    private IAboutVendorService aboutVendorService;

    /** Object used to invoke the operations of the IContractServe interface */
    private final IContractService contractService;

    /**
     * Object used to invoke the operations of the HE-01 IListContractualFolders
     * interface
     */
    // private final IListContractualFolders listContractualFolders;

    /**
     * constructor method
     */
    public ContractController(IContractService contractService/* , IListContractualFolders listContractualFolders */) {
        this.contractService = contractService;
        // this.listContractualFolders = listContractualFolders;
    }

    /**
     * API to get a Contract by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<ContractDtoFindResponse>> getById(@Valid @PathVariable final Integer id) {
        return new ResponseEntity<>(this.contractService.getById(id), HttpStatus.OK);
    }

    /**
     * API to get all Contracts
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
        return new ResponseEntity<>(this.contractService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to create a Contract
     *
     * @param ContractDtoCreateRequest {@link ContractDtoCreateRequest} Object with
     *                                 the information to be inserted, received in
     *                                 the body of the request to the service
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<ContractDtoCreateResponse>> createContract(
            @Valid @RequestBody final ContractDtoCreateRequest ContractDtoCreateRequest) {
        return new ResponseEntity<>(this.contractService.createContract(ContractDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to update a Contract
     *
     * @param contractDtoUpdateRequest {@link ContractDtoUpdateRequest} Object with
     *                                 the information to be updated, received in
     *                                 the body of the request to the service
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @PatchMapping("")
    public ResponseEntity<Response<ContractDtoCreateResponse>> updateContract(
            @Valid @RequestBody final ContractDtoUpdateRequest contractDtoUpdateRequest) {
        return new ResponseEntity<>(this.contractService.updateContract(contractDtoUpdateRequest), HttpStatus.OK);
    }

    /**
     * API to delete a Contract by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteContract(@Valid @PathVariable final Integer id) {
        return new ResponseEntity<>(this.contractService.deleteContract(id), HttpStatus.OK);
    }

    /**
     * API to get all contracts sort by update time descending
     *
     * @param pageNo   Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @GetMapping("contractualFolders")
    public ResponseEntity<Response<PageableResponse<Object>>> getContractualFoldersSortBySigningDate(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(
                this.contractService.getContractualFoldersSortBySigningDate(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to get all contracts sort by update time descending
     *
     * @param pageNo   Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @GetMapping("contractualFoldersFilterPattern")
    public ResponseEntity<Response<PageableResponse<Object>>> getContractualFoldersByFilter(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam() String filter,
            @RequestParam() String search) {
        return new ResponseEntity<>(
                this.contractService.getContractualFoldersByFilter(pageNo, pageSize, filter, search),
                HttpStatus.OK);
    }

    /**
     * API to get Contract Existence Verification
     * 
     * @param prmMask - The contract mask used to verify its existence
     * @return {@link Response} - Response object for the service, containing
     */

    @PostMapping("existingContractByMask")
    public ResponseEntity<Response<Boolean>> getExistingContractByMask(
            @Valid @RequestBody final ContractDtoIdRequest contractDtoIdRequest) {
        return new ResponseEntity<>(
                this.contractService.entityExistsByReference(contractDtoIdRequest),
                HttpStatus.OK);
    }

    /**
     * API to get Existence evaluation for a contract based on reference
     * 
     * @param prmMask - The contract mask used to verify its existence
     * @return {@link Response} - Response object for the service, containing
     */

    @PostMapping("existingEvaluationContractByMask")
    public ResponseEntity<Response<Boolean>> getExistingEvaluationContractByMask(
            @Valid @RequestBody final ContractDtoIdRequest contractDtoIdRequest) {
        return new ResponseEntity<>(
                this.contractService.ExistEvaluationByReference(contractDtoIdRequest),
                HttpStatus.OK);
    }

    /**
     * API to get data for a contract and vendor
     * 
     * @param prmMask - The contract mask used to verify its existence
     * @return {@link Response} - Response object for the service, containing
     */

    @PostMapping("dataContractVendorByMask")
    public ResponseEntity<Response<ContractVendorDtoResponse>> getDataContractVendorByMask(
            @Valid @RequestBody final ContractDtoIdRequest contractDtoIdRequest) {
        return new ResponseEntity<>(
                this.contractService.DataContractVendorByMask(contractDtoIdRequest),
                HttpStatus.OK);
    }

    @GetMapping("/aboutVendor")
    public Response<aboutVendorDto> getCriteriaByType(@RequestParam String referenceMask) {

        return aboutVendorService.getAboutVendor(referenceMask);
    }


    //Method for calculating the average of the different types of contracts
    @GetMapping("/averageContractType/Servicios/{year}")
    public Response<ContractDtoAverageRequest> getAverageCategoryByServices(@PathVariable int year) {
        return contractService.getAverageContractByCategory("Servicios", year);
    }

    //Method for calculating the average of the different types of contracts
    @GetMapping("/averageContractType/Bienes/{year}")
    public Response<ContractDtoAverageRequest> getAverageCategoryByGoods(@PathVariable int year) {
        return contractService.getAverageContractByCategory("Bienes", year);
    }

    //Method for calculating the average of the different types of contracts
    @GetMapping("/averageContractType/Obras/{year}")
    public Response<ContractDtoAverageRequest> getAverageCategoryByWorks(@PathVariable int year) {
        return contractService.getAverageContractByCategory("Obras", year);
    }

    
}
