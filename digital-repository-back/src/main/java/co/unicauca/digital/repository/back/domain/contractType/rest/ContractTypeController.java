package co.unicauca.digital.repository.back.domain.contractType.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.unicauca.digital.repository.back.domain.contractType.dto.request.ContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractType.dto.request.ContractTypeDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.contractType.dto.response.ContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractType.dto.response.ContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.contractType.service.IContractTypeService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

@RestController
@RequestMapping("/contractType")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class ContractTypeController {
    
    /** Object used to invoke the operations of the IModalityService interface */
    private final IContractTypeService contractTypeService;

    /*Constructor */

    ContractTypeController(IContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    /**
     * API Method to get ContractType by ID
     * @param id Object ID  
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<ContractTypeDtoFindResponse>> getById(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.contractTypeService.getById(id), HttpStatus.OK);
    }

    /**
     * Api Method to get all contract types
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size 
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("")
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "30", required = false) int pageSize){
            return new ResponseEntity<>(this.contractTypeService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to create a Contract Type
     *
     * @param contractTypeDtoCreateRequest {@link ContractTypeDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<ContractTypeDtoCreateResponse>> createModality(@Valid @RequestBody final ContractTypeDtoCreateRequest contractTypeDtoCreateRequest){
        return new ResponseEntity<>(this.contractTypeService.createContractType(contractTypeDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to update a Contract Type
     *
     * @param contractTypeDtoUpdateRequest {@link ContractTypeDtoUpdateRequest} Object with the information to be updated, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PatchMapping("")
    public ResponseEntity<Response<ContractTypeDtoCreateResponse>> updateModality(@Valid @RequestBody final ContractTypeDtoUpdateRequest contractTypeDtoUpdateRequest){
        return new ResponseEntity<>(this.contractTypeService.updateContractType(contractTypeDtoUpdateRequest), HttpStatus.OK);
    }

     /**
     * API to delete a Contract Type by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteModality(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.contractTypeService.deleteContractType(id), HttpStatus.OK);
    }
}