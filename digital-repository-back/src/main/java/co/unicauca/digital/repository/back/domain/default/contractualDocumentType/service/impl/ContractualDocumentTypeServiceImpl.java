package co.unicauca.digital.repository.back.domain.contractualDocumentType.service.impl;

import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.request.ContractualDocumentTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response.ContractualDocumentTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response.ContractualDocumentTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.mapper.IContractualDocumentTypeMapper;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.model.ContractualDocumentType;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.repository.IContractualDocumentTypeRepository;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.service.IContractualDocumentTypeService;
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
public class ContractualDocumentTypeServiceImpl implements IContractualDocumentTypeService {

    /** Object to perform CRUD operations on the ContractualDocumentType entity */
    private final IContractualDocumentTypeRepository contractualDocumentTypeRepository;

    /** Mapping object for mapping the ContractualDocumentTypes */
    private final IContractualDocumentTypeMapper contractualDocumentTypeMapper;

    public ContractualDocumentTypeServiceImpl(IContractualDocumentTypeRepository contractualDocumentTypeRepository, IContractualDocumentTypeMapper contractualDocumentTypeMapper) {
        this.contractualDocumentTypeRepository = contractualDocumentTypeRepository;
        this.contractualDocumentTypeMapper = contractualDocumentTypeMapper;
    }

    @Override
    public Response<ContractualDocumentTypeDtoCreateResponse> createContractualDocumentType(ContractualDocumentTypeDtoCreateRequest contractualDocumentTypeDtoCreateRequest) {

        ContractualDocumentType contractualDocumentType = contractualDocumentTypeMapper.toEntityCreate(contractualDocumentTypeDtoCreateRequest);
        contractualDocumentType.setCreateTime(LocalDateTime.now());

        ContractualDocumentType contractualDocumentTypeSaved = this.contractualDocumentTypeRepository.save(contractualDocumentType);
        ContractualDocumentTypeDtoCreateResponse contractualDocumentTypeDtoCreateResponse = contractualDocumentTypeMapper.toDtoCreate(contractualDocumentTypeSaved);

        return new ResponseHandler<>(200, "Tipo de documento contractual creado exitosamente", "Tipo de documento contractual creado exitosamente", contractualDocumentTypeDtoCreateResponse).getResponse();


    }

    @Override
    public Response<ContractualDocumentTypeDtoFindResponse> getById(int id) {
        if(!entityExistsByIdentification(id)){
            throw new BusinessRuleException("contractualDocumentTypeRequest.request.not.found");
        }

        ContractualDocumentTypeDtoFindResponse contractualDocumentTypeDtoFindResponse = this.contractualDocumentTypeMapper.toDtoFind(this.contractualDocumentTypeRepository.findById(id).get());
        return new ResponseHandler<>(200, "Encontrado", "Encontrado", contractualDocumentTypeDtoFindResponse).getResponse();
    }

    @Override
    public Response<PageableResponse<Object>> getAll(int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ContractualDocumentType> responsePage = this.contractualDocumentTypeRepository.findAll(pageRequest);

        List<Object> contractualDocumentTypeDtoFindResponses = responsePage.get().map(
                this.contractualDocumentTypeMapper::toDtoFind
        ).collect(Collectors.toList());


        PageableResponse<Object> response = PageableResponse.builder()
                .data(contractualDocumentTypeDtoFindResponses)
                .pageNo(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .build();

        return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();

    }

    @Override
    public Response<Boolean> deleteContractualDocumentTypes(int id) {
        Optional<ContractualDocumentType> contractualDocumentType = this.contractualDocumentTypeRepository.findById(id);

        if (contractualDocumentType.isEmpty()) throw new BusinessRuleException("contractualDocumentTypeRequest.request.not.found");
        this.contractualDocumentTypeRepository.deleteById(id);

        return new ResponseHandler<>(200, "Tipo de documento contractual eliminado exitosamente", "Tipo de documento contractual eliminado exitosamente", contractualDocumentTypeRepository.existsById(id)).getResponse();
    }

    private boolean entityExistsByIdentification(final Integer id) {
        return this.contractualDocumentTypeRepository.existsById(id);
    }

}
