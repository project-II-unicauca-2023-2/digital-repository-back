package co.unicauca.digital.repository.back.domain.contractType.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.contractType.dto.request.ContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractType.dto.request.ContractTypeDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.contractType.dto.response.ContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractType.dto.response.ContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.contractType.mapper.IContractTypeMapper;
import co.unicauca.digital.repository.back.domain.contractType.model.ContractType;
import co.unicauca.digital.repository.back.domain.contractType.repository.IContractTypeRepository;
import co.unicauca.digital.repository.back.domain.contractType.service.IContractTypeService;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;

/** Class in charge of implementing the IContractTypeService interface {@link IContractTypeService} */
@Service
@Primary
public class ContractTypeServiceImpl implements IContractTypeService{

    /** Object to perform CRUD operations on the ContractType entity */
    private final IContractTypeRepository contractTypeRepository;

    /** Mapping object for mapping the ContractTypes */
    private final IContractTypeMapper contractTypeMapper;

    /*Constructor */
    ContractTypeServiceImpl(IContractTypeRepository contractTypeRepository, IContractTypeMapper contractTypeMapper) {
        this.contractTypeRepository = contractTypeRepository;
        this.contractTypeMapper = contractTypeMapper;
    }

    /**
     * @see IContractTypeService#getById(int)
     */
    @Override
    public Response<ContractTypeDtoFindResponse> getById(int id) {
        Optional<ContractType> modalityFound = this.contractTypeRepository.findById(id);

        if(modalityFound.isEmpty()) throw new BusinessRuleException("contractType.request.not.found");

        ContractTypeDtoFindResponse modalityDtoFindResponse = contractTypeMapper.toDtoFind(modalityFound.get());

        return new ResponseHandler<>(200, "Encontrado", "Encontrado", modalityDtoFindResponse).getResponse();
    }

    /**
     * @see IContractTypeService#getAll(int, int)
     */
    @Override
    public Response<PageableResponse<Object>> getAll(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ContractType> responsePage = contractTypeRepository.findAll(pageRequest);
        
        List<Object> contractTypeDtoFindResponses = responsePage.get().map(
            contractTypeMapper::toDtoFind
        ).collect(Collectors.toList());

        PageableResponse<Object> response = PageableResponse.builder()
        .data(contractTypeDtoFindResponses)
        .pageNo(responsePage.getNumber())
        .pageSize(responsePage.getSize())
        .totalElements(responsePage.getTotalElements())
        .totalPages(responsePage.getTotalPages())
        .last(responsePage.isLast())
        .build();

        return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();
    }

    /**
     * @see IContractTypeService#createContractType(ContractTypeDtoCreateRequest)
     */
    @Override
    public Response<ContractTypeDtoCreateResponse> createContractType(
            ContractTypeDtoCreateRequest contractTypeDtoCreateRequest) {
        if(entityExistsByExternalCode(contractTypeDtoCreateRequest.getExternalCode())) throw new BusinessRuleException("modality.request.already.exists");

        ContractType contractType = contractTypeMapper.toEntityCreate(contractTypeDtoCreateRequest);
        contractType.setCreateTime(LocalDateTime.now());
        //TODO: Set Create User

        ContractType contractTypeSaved = this.contractTypeRepository.save(contractType);
        ContractTypeDtoCreateResponse contractTypeDtoCreateResponse = contractTypeMapper.toDtoCreate(contractTypeSaved);

        return new ResponseHandler<>(200, "Tipo de contrado creado exitosamente", "Tipo de contrado creado exitosamente", contractTypeDtoCreateResponse).getResponse();
                
    }

    /**
     * @see IContractTypeService#updateContractType(ContractTypeDtoUpdateRequest)
     */
    @Override
    public Response<ContractTypeDtoCreateResponse> updateContractType(
            ContractTypeDtoUpdateRequest contractTypeDtoUpdateRequest) {
                Optional<ContractType> contractType = contractTypeRepository.findById(contractTypeDtoUpdateRequest.getId());

                if (contractType.isEmpty()) throw new BusinessRuleException("contractType.request.not.found");
        
                ContractType updatedContractType = ContractType.builder()
                        .id(contractType.get().getId())
                        .name(contractTypeDtoUpdateRequest.getName())
                        .externalCode(contractTypeDtoUpdateRequest.getExternalCode())
                        .createUser(contractType.get().getCreateUser())
                        .createTime(contractType.get().getCreateTime())
                        .updateTime(LocalDateTime.now())
                        .updateUser(contractType.get().getUpdateUser())
                        .build();
        
                        ContractType contractTypeSaved = this.contractTypeRepository.save(updatedContractType);
                        ContractTypeDtoCreateResponse contractTypeDtoCreateResponse = contractTypeMapper.toDtoCreate(contractTypeSaved);
        
                return new ResponseHandler<>(200, "Tipo de contrado actualizado exitosamente", "Tipo de contrado actualizado exitosamente", contractTypeDtoCreateResponse).getResponse();
            }

    /**
     * @see IContractTypeService#deleteContractType(int)
     */
    @Override
    public Response<Boolean> deleteContractType(int id) {
        Optional<ContractType> contractType = contractTypeRepository.findById(id);

        if (contractType.isEmpty()) throw new BusinessRuleException("contractType.request.not.found");
        contractTypeRepository.deleteById(id);

        return new ResponseHandler<>(200, "Tipo de contrado eliminado exitosamente", "Tipo de contrado eliminado exitosamente", !contractTypeRepository.existsById(id)).getResponse();
    }

      /**
     * Check if the ModalityDtoRequest already exists
     *
     * @param externalCode the request to be validated
     * @return true if the entity exists
     */
    private boolean entityExistsByExternalCode(final String externalCode) {
        return contractTypeRepository.findByExternalCode(externalCode).isPresent();
    }

  
}