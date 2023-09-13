package co.unicauca.digital.repository.back.domain.document.rest;

import co.unicauca.digital.repository.back.domain.document.dto.request.DocumentDtoRequest;
import co.unicauca.digital.repository.back.domain.document.dto.response.DocumentDtoResponse;
import co.unicauca.digital.repository.back.domain.document.service.IDocumentService;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/document")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class DocumentController {
    private final IDocumentService documentService;

    public DocumentController(IDocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Response<DocumentDtoResponse>> createDocument(
            @Valid @RequestBody final DocumentDtoRequest documentDtoRequest) {
        return new ResponseEntity<>(this.documentService.createDocument(documentDtoRequest), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<Response<List<DocumentDtoResponse>>> saveDocuments(
            @Valid @RequestBody List<DocumentDtoRequest> documents) {
        return new ResponseEntity<>(this.documentService.createDocuments(documents), HttpStatus.OK);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<Response<DocumentDtoResponse>> getByIdDocument(@PathVariable("documentId") int id) {
        return new ResponseEntity<>(this.documentService.getByIdDocument(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Response<PageableResponse<Object>>> getAll(
            @RequestParam(defaultValue = "pageNumber") int pageNumber,
            @RequestParam(defaultValue = "pageSize") int pageSize) {
        return new ResponseEntity<>(this.documentService.getAll(pageNumber, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable int id) {
        return new ResponseEntity<>(this.documentService.delete(id), HttpStatus.OK);
    }

}