package co.unicauca.digital.repository.back.domain.vendor.rest;

import co.unicauca.digital.repository.back.domain.vendor.dto.request.VendorDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.vendor.dto.request.VendorDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.vendor.dto.response.VendorDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.vendor.dto.response.VendorDtoFindResponse;
import co.unicauca.digital.repository.back.domain.vendor.service.IVendorService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VendorController {

    /** Object used to invoke the operations of the IContractServe interface */
    private final IVendorService vendorService;

    /**
     * constructor method
     */
    public VendorController(IVendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * API to get a Vendor by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<VendorDtoFindResponse>> getById(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.vendorService.getById(id), HttpStatus.OK);
    }

    /**
     * API to get all Vendors
     *
     * @param pageNo Pagination Page number
     * @param pageSize Pagination Page size
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @GetMapping("")
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(this.vendorService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    /**
     * API to create a Vendor
     *
     * @param vendorDtoCreateRequest {@link VendorDtoCreateRequest} Object with the information to be inserted, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PostMapping("")
    public ResponseEntity<Response<VendorDtoCreateResponse>> createVendor(@Valid @RequestBody final VendorDtoCreateRequest vendorDtoCreateRequest){
        return new ResponseEntity<>(this.vendorService.createVendor(vendorDtoCreateRequest), HttpStatus.OK);
    }

    /**
     * API to update a Vendor
     *
     * @param vendorDtoUpdateRequest {@link VendorDtoUpdateRequest} Object with the information to be updated, received in the body of the request to the service
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @PatchMapping("")
    public ResponseEntity<Response<VendorDtoCreateResponse>> updateVendor(@Valid @RequestBody final VendorDtoUpdateRequest vendorDtoUpdateRequest){
        return new ResponseEntity<>(this.vendorService.updateVendor(vendorDtoUpdateRequest), HttpStatus.OK);
    }

    /**
     * API to delete a Vendor by ID
     *
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains information about the outcome of the transaction.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteVendor(@Valid @PathVariable final Integer id){
        return new ResponseEntity<>(this.vendorService.deleteVendor(id), HttpStatus.OK);
    }
}
