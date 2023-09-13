package co.unicauca.digital.repository.back.domain.internalNormative.rest;

import co.unicauca.digital.repository.back.domain.internalNormative.dto.request.*;
import co.unicauca.digital.repository.back.domain.internalNormative.dto.response.*;
import co.unicauca.digital.repository.back.domain.internalNormative.service.IInternalNormativeService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/internalNormative")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InternalNormativeController {

    /** Object used to invoke the operations of the IInternalNormativeService interface */
    private final IInternalNormativeService internalNormativeService;

    /**
     * constructor method
     */
    public InternalNormativeController(IInternalNormativeService internalNormativeService) {
        this.internalNormativeService = internalNormativeService;
    }

    /**
     * API to create an internal normative
     *
     * @param internalNormativeDtoCreateRequest {@link InternalNormativeDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<InternalNormativeDtoCreateResponse>> createInternalNormative(@Valid @RequestBody final InternalNormativeDtoCreateRequest internalNormativeDtoCreateRequest){
        return new ResponseEntity<>(this.internalNormativeService.createInternalNormative(internalNormativeDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to get an internal normative by id
     *
     * @param id {@link Integer} Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<InternalNormativeDtoFindResponse>> getById(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.internalNormativeService.getById(id), HttpStatus.OK);
    }

    /**
     * API to get all records from internal normative table
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("")
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(this.internalNormativeService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to update a internal normative
     *
     * @param internalNormativeDtoUpdateRequest {@link InternalNormativeDtoUpdateRequest} Object with the information to be updated, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PatchMapping("")
    public ResponseEntity<Response<InternalNormativeDtoCreateResponse>> updateInternalNormative(@Valid @RequestBody final InternalNormativeDtoUpdateRequest internalNormativeDtoUpdateRequest){
        return new ResponseEntity<>(this.internalNormativeService.updateInternalNormative(internalNormativeDtoUpdateRequest), HttpStatus.OK);
    }

    /**
     * API to delete an internal normative by id
     *
     * @param id {@link Integer} Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> deleteInternalNormative(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.internalNormativeService.deleteInternalNormative(id), HttpStatus.OK);
    }
}
