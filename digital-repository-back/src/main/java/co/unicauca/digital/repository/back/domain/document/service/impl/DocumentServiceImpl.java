package co.unicauca.digital.repository.back.domain.document.service.impl;

import co.unicauca.digital.repository.back.domain.collection.model.Collection;
import co.unicauca.digital.repository.back.domain.collection.repository.ICollectionRepository;
import co.unicauca.digital.repository.back.domain.document.dto.request.DocumentDtoRequest;
import co.unicauca.digital.repository.back.domain.document.dto.response.DocumentDtoResponse;
import co.unicauca.digital.repository.back.domain.document.mapper.IDocumentMapper;
import co.unicauca.digital.repository.back.domain.document.model.Document;
import co.unicauca.digital.repository.back.domain.document.repository.IDocumentRepository;
import co.unicauca.digital.repository.back.domain.document.service.IDocumentService;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements IDocumentService {

    private final IDocumentRepository documentRepository;
    private final ICollectionRepository collectionRepository;
    private final IDocumentMapper documentMapper;

    public DocumentServiceImpl(IDocumentRepository documentRepository, IDocumentMapper documentMapper, ICollectionRepository collectionRepository) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.collectionRepository = collectionRepository;
    }

    @Override
    @Transactional
    public Response<DocumentDtoResponse> createDocument(DocumentDtoRequest documentDtoRequest) {
        Optional<Collection> collection = this.collectionRepository.findCollectionByContractIdAndContractualDocumentId(documentDtoRequest.getContractId(), documentDtoRequest.getContractualDocumentId());
        if(collection.isEmpty()){
            throw new BusinessRuleException("document.request.collectionId.not.found");
        }
        Document document = this.documentMapper.toEntity(documentDtoRequest);
        document.setCreateTime(LocalDateTime.now());
        Document entitySave = this.documentRepository.save(document);
        DocumentDtoResponse documentDtoResponse = this.documentMapper.toDto(entitySave);
        return new ResponseHandler<>(200,"Documento guardado con éxito", "",documentDtoResponse).getResponse();
    }

    @Override
    @Transactional
    public Response<List<DocumentDtoResponse>> createDocuments(List<DocumentDtoRequest> documents) {
        List<DocumentDtoResponse> documentDtoResponses = new ArrayList<>();
        for (DocumentDtoRequest request : documents) {
            Optional<Collection> collection = this.collectionRepository.findCollectionByContractIdAndContractualDocumentId(request.getContractId(), request.getContractualDocumentId());
            if(collection.isEmpty()){
                throw new BusinessRuleException("document.request.documents.collectionId.not.found");
            }
        }
        for (DocumentDtoRequest request : documents) {
            Optional<Collection> collection = this.collectionRepository.findCollectionByContractIdAndContractualDocumentId(request.getContractId(), request.getContractualDocumentId());
            if(collection.isEmpty()){
                throw new BusinessRuleException("document.request.collectionId.not.found");
            }
            documentDtoResponses.add(createDocument(request).getData());
        }
        return new ResponseHandler<>(200,"Documentos guardados con éxito", "",documentDtoResponses).getResponse();
    }

    @Override
    public Response<DocumentDtoResponse> getByIdDocument(Integer id) {
        Optional<Document> documentFound = documentRepository.findById(id);
        if(documentFound.isEmpty()){
            throw new BusinessRuleException("document.request.not.found");
        }
        DocumentDtoResponse documentDtoResponse = this.documentMapper.toDto(documentFound.get());
        return new ResponseHandler<>(200,"Documento encontrado", "",documentDtoResponse).getResponse();
    }

    @Override
    public Response<PageableResponse<Object>> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        Page<Document> page = this.documentRepository.findAll(pageRequest);
        List<Object> documentList = page.get().map(
                this.documentMapper::toDto
        ).collect(Collectors.toList());
        PageableResponse<Object> response = PageableResponse.builder()
                .data(documentList)
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .last(page.isLast())
                .build();
        return new ResponseHandler<>(200,"Documentos encontrados", "",response).getResponse();
    }

    @Override
    public Response<Boolean> delete(Integer id) {
        Optional<Document> document = this.documentRepository.findById(id);
        if(document.isEmpty())
            throw new BusinessRuleException("document.request.not.found");
        this.documentRepository.deleteById(id);
        return new ResponseHandler<>(200,"Documento Eliminado", "", documentExist(id)).getResponse();
    }

    private boolean documentExist(final Integer id){
        return !this.documentRepository.existsById(id);
    }
}
