package co.unicauca.digital.repository.back.domain.modality.rest;

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

import co.unicauca.digital.repository.back.domain.modality.dto.request.ModalityDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.modality.dto.request.ModalityDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.modality.dto.response.ModalityDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.modality.dto.response.ModalityDtoFindResponse;
import co.unicauca.digital.repository.back.domain.modality.service.IModalityService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;

@RestController
@RequestMapping("/modality")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class ModalityController {
    
    /** Object used to invoke the operations of the IModalityService interface */
    private final IModalityService modalityService;

    /*Constructor */

    ModalityController(IModalityService modalityService) {
        this.modalityService = modalityService;
    }

    /**
     * API Method to get Modality by ID
     * @param id Object ID  
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<ModalityDtoFindResponse>> getById(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.modalityService.getById(id), HttpStatus.OK);
    }

    /**
     * Api Method to get all modalities
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size 
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("")
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "30", required = false) int pageSize){
            return new ResponseEntity<>(this.modalityService.getAll(pageNo, pageSize), HttpStatus.OK);
    }


    /**
     * API to create a modality
     *
     * @param modalityDtoCreateRequest {@link ModalityDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<ModalityDtoCreateResponse>> createModality(@Valid @RequestBody final ModalityDtoCreateRequest modalityDtoCreateRequest){
        return new ResponseEntity<>(this.modalityService.createModality(modalityDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to update a Modality
     *
     * @param modalityDtoUpdateRequest {@link ModalityDtoUpdateRequest} Object with the information to be updated, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PatchMapping("")
    public ResponseEntity<Response<ModalityDtoCreateResponse>> updateModality(@Valid @RequestBody final ModalityDtoUpdateRequest modalityDtoUpdateRequest){
        return new ResponseEntity<>(this.modalityService.updateModality(modalityDtoUpdateRequest), HttpStatus.OK);
    }


    /**
     * API to delete a modality by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteModality(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.modalityService.deleteModality(id), HttpStatus.OK);
    }


}
