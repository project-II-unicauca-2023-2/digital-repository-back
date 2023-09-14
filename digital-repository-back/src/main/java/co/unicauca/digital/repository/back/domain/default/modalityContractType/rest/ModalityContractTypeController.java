package co.unicauca.digital.repository.back.domain.modalityContractType.rest;

import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoFindResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.request.ModalityContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.request.ModalityContractTypeDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.response.ModalityContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.response.ModalityContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.service.IModalityContractTypeService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/modalityContractType")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ModalityContractTypeController {

    /** Object used to invoke the operations of the IContractServe interface */
    private final IModalityContractTypeService modalityContractTypeService;

    /**
     * constructor method
     */
    public ModalityContractTypeController(IModalityContractTypeService modalityContractTypeService){
        this.modalityContractTypeService = modalityContractTypeService;
    }

    /**
     * API to create a modality contract type
     *
     * @param modalityContractTypeDtoCreateRequest {@link ModalityContractTypeDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<ModalityContractTypeDtoCreateResponse>> createModalityContractType(@Valid @RequestBody final ModalityContractTypeDtoCreateRequest modalityContractTypeDtoCreateRequest){
        return new ResponseEntity<>(this.modalityContractTypeService.createModalityContractType(modalityContractTypeDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to get a modality contract type by id
     *
     * @param id {@link Integer} Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<ModalityContractTypeDtoFindResponse>> getById(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.modalityContractTypeService.getById(id), HttpStatus.OK);
    }

    /**
     * API to get all records from modality contract type table
     *
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("")
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(this.modalityContractTypeService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

//    /**
//     * API to get a modality contract type by id
//     *
//     * @param contractTypeId {@link Integer} ID contractType to do the search
//     * @param modalityId {@link Integer} ID modality to do the search
//     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
//     */
//    @GetMapping("")
//    public ResponseEntity<Response<ModalityContractTypeDtoFindResponse>> getByContractModality(
//            @RequestParam(value = "contractTypeId", required = true) Integer contractTypeId,
//            @RequestParam(value = "modalityId", required = true) Integer modalityId) {
//        return new ResponseEntity<>(this.modalityContractTypeService.getByContractModality(contractTypeId, modalityId), HttpStatus.OK);
//    }

    /**
     * API to update a modality contract type
     *
     * @param modalityContractTypeDtoUpdateRequest {@link ModalityContractTypeDtoUpdateRequest} Object with the information to be updated, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PatchMapping("")
    public ResponseEntity<Response<ModalityContractTypeDtoCreateResponse>> updateModalityContractType(@Valid @RequestBody final ModalityContractTypeDtoUpdateRequest modalityContractTypeDtoUpdateRequest){
        return new ResponseEntity<>(this.modalityContractTypeService.updateModalityContractType(modalityContractTypeDtoUpdateRequest), HttpStatus.OK);
    }

    /**
     * API to delete a modality contract type by id
     *
     * @param id {@link Integer} Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> deleteModalityContractType(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.modalityContractTypeService.deleteModalityContractType(id), HttpStatus.OK);
    }

    /**
     * API to find a CheckList by id
     *
     * @param id {@link Integer} Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */

    @GetMapping("check-list/{id}")
    public ResponseEntity<Response<List<ContractualDocumentDtoFindResponse>>> getCheckList(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.modalityContractTypeService.getCheckListById(id), HttpStatus.OK);
    }

}
