package co.unicauca.digital.repository.back.domain.collection.rest;

import co.unicauca.digital.repository.back.domain.collection.dto.request.CollectionDtoRequest;
import co.unicauca.digital.repository.back.domain.collection.dto.response.CollectionDtoResponse;
import co.unicauca.digital.repository.back.domain.collection.service.ICollectionService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/collection")
@Validated
public class CollectionController {
    private final ICollectionService collectionService;

    public CollectionController(ICollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping
    public ResponseEntity<Response<CollectionDtoResponse>> createCollection(@Valid @RequestBody final CollectionDtoRequest collectionDtoRequest){
        return new ResponseEntity<>(this.collectionService.createCollection(collectionDtoRequest), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<Response<List<CollectionDtoResponse>>> saveDocuments(@RequestBody final List<CollectionDtoRequest> collectionDtoRequests){
        return new ResponseEntity<>(this.collectionService.saveDocuments(collectionDtoRequests), HttpStatus.OK);
    }

    @GetMapping("/{collectionId}")
    public ResponseEntity<Response<CollectionDtoResponse>> getByIdCollection(@PathVariable("collectionId") int id){
        return new ResponseEntity<>(this.collectionService.getByIdCollection(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(@RequestParam(defaultValue = "pageNumber") int pageNumber, @RequestParam(defaultValue = "pageSize") int pageSize){
        return new ResponseEntity<>(this.collectionService.getAll(pageNumber,pageSize),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response<CollectionDtoResponse>> update(@Valid @RequestBody CollectionDtoRequest collectionDtoRequest){
        return new ResponseEntity<>(this.collectionService.update(collectionDtoRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable int id){
        return new ResponseEntity<>(this.collectionService.delete(id),HttpStatus.OK);
    }

    @GetMapping("/{contractualDocumentId}/{contractId}")
    public ResponseEntity<Response<CollectionDtoResponse>> getCollectionIdByContractualdocumentAndContract(@PathVariable("contractualDocumentId") int contractualDocumentId , @PathVariable("contractId") int contractId){
        return new ResponseEntity<>(this.collectionService.getCollectionIdByContractualdocumentAndContract(contractualDocumentId,contractId),HttpStatus.OK);
    }

}
