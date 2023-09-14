package co.unicauca.digital.repository.back.domain.contractualDocument.service.impl;

import co.unicauca.digital.repository.back.domain.contractualDocument.dto.request.ContractualDocumentDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoFindResponse;
import co.unicauca.digital.repository.back.domain.contractualDocument.mapper.IContractualDocumentMapper;
import co.unicauca.digital.repository.back.domain.contractualDocument.model.ContractualDocument;
import co.unicauca.digital.repository.back.domain.contractualDocument.repository.IContractualDocumentRepository;
import co.unicauca.digital.repository.back.domain.contractualDocument.service.IContractualDocumentService;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.model.ContractualDocumentType;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.repository.IContractualDocumentTypeRepository;
import co.unicauca.digital.repository.back.domain.modalityContractType.model.ModalityContractType;
import co.unicauca.digital.repository.back.domain.modalityContractType.repository.IModalityContractTypeRepository;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class ContractualDocumentServiceImpl implements IContractualDocumentService {

    /** Object to perform CRUD operations on the ContractualDocument entity */
    private final IContractualDocumentRepository contractualDocumentRepository;

    /** Object to perform CRUD operations on the ContractualDocumentType entity */
    private final IContractualDocumentTypeRepository contractualDocumentTypeRepository;

    /** Object to perform CRUD operations on the ModalityContractType entity */
    private final IModalityContractTypeRepository modalityContractTypeRepository;

    /** Mapping object for mapping the ContractualDocument */
    private final IContractualDocumentMapper contractualDocumentMapper;


    public ContractualDocumentServiceImpl(IContractualDocumentRepository contractualDocumentRepository, IContractualDocumentTypeRepository contractualDocumentTypeRepository, IModalityContractTypeRepository modalityContractTypeRepository, IContractualDocumentMapper contractualDocumentMapper) {
        this.contractualDocumentRepository = contractualDocumentRepository;
        this.contractualDocumentTypeRepository = contractualDocumentTypeRepository;
        this.modalityContractTypeRepository = modalityContractTypeRepository;
        this.contractualDocumentMapper = contractualDocumentMapper;
    }

    @Override
    public Response<ContractualDocumentDtoCreateResponse> createContractualDocumentType(ContractualDocumentDtoCreateRequest contractualDocumentDtoCreateRequest) {

        if(!this.contractualDocumentTypeRepository.existsById(contractualDocumentDtoCreateRequest.getContractualDocumentType()))
            throw new BusinessRuleException("contractualDocumentTypeRequest.request.not.found");

        ContractualDocumentType contractualDocumentType = this.contractualDocumentTypeRepository.getReferenceById(contractualDocumentDtoCreateRequest.getContractualDocumentType());

        if(!this.modalityContractTypeRepository.existsById(contractualDocumentDtoCreateRequest.getModalityContractType()))
            throw new BusinessRuleException("modalityContractType.request.not.found");

        ModalityContractType modalityContractType = this.modalityContractTypeRepository.getReferenceById(contractualDocumentDtoCreateRequest.getModalityContractType());

        ContractualDocument contractualDocument = this.contractualDocumentMapper.toEntityCreate(contractualDocumentDtoCreateRequest);
        contractualDocument.setContractualDocumentType(contractualDocumentType);
        contractualDocument.setModalityContractType(modalityContractType);
        contractualDocument.setCreateTime(LocalDateTime.now());

        ContractualDocument contractualDocumentSaved = this.contractualDocumentRepository.save(contractualDocument);
        ContractualDocumentDtoCreateResponse contractualDocumentDtoCreateResponse = this.contractualDocumentMapper.toDtoCreate(contractualDocumentSaved);

        return new ResponseHandler<>(200, "Documento contractual creado exitosamente", "Documento contractual creado exitosamente", contractualDocumentDtoCreateResponse).getResponse();
    }

    @Override
    public Response<ContractualDocumentDtoFindResponse> getById(int id) {
        if(!entityExistsById(id)){
            throw new BusinessRuleException("contractualDocumentRequest.request.not.found");
        }

        ContractualDocumentDtoFindResponse contractualDocumentDtoFindResponse = this.contractualDocumentMapper.toDtoFind(this.contractualDocumentRepository.findById(id).get());
        return new ResponseHandler<>(200, "Encontrado", "Encontrado", contractualDocumentDtoFindResponse).getResponse();
    }

    @Override
    public Response<PageableResponse<Object>> getAll(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ContractualDocument> responsePage = this.contractualDocumentRepository.findAll(pageRequest);

        List<Object> contractualDocumentDtoFindResponses = responsePage.get().map(
                this.contractualDocumentMapper::toDtoFind
        ).collect(Collectors.toList());


        PageableResponse<Object> response = PageableResponse.builder()
                .data(contractualDocumentDtoFindResponses)
                .pageNo(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .build();

        return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();
    }

    @Override
    public Response<Boolean> deleteContractualDocument(int id) {
        Optional<ContractualDocument> contractualDocument = this.contractualDocumentRepository.findById(id);

        if (contractualDocument.isEmpty()) throw new BusinessRuleException("contractualDocumentRequest.request.not.found");
        this.contractualDocumentRepository.deleteById(id);

        return new ResponseHandler<>(200, "Documento contractual eliminado exitosamente", "Documento contractual eliminado exitosamente", this.contractualDocumentRepository.existsById(id)).getResponse();
    }

    private boolean entityExistsById(final Integer id) {
        return this.contractualDocumentRepository.existsById(id);
    }
}
