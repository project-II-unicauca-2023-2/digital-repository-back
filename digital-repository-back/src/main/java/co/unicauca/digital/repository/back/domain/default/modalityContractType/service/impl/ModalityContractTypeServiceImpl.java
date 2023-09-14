package co.unicauca.digital.repository.back.domain.modalityContractType.service.impl;

import co.unicauca.digital.repository.back.domain.contractType.model.ContractType;
import co.unicauca.digital.repository.back.domain.contractType.repository.IContractTypeRepository;
import co.unicauca.digital.repository.back.domain.contractualDocument.dto.response.ContractualDocumentDtoFindResponse;
import co.unicauca.digital.repository.back.domain.contractualDocument.mapper.IContractualDocumentMapper;
import co.unicauca.digital.repository.back.domain.internalNormative.model.InternalNormative;
import co.unicauca.digital.repository.back.domain.internalNormative.repository.IInternalNormativeRepository;
import co.unicauca.digital.repository.back.domain.modality.model.Modality;
import co.unicauca.digital.repository.back.domain.modality.repository.IModalityRepository;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.request.ModalityContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.request.ModalityContractTypeDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.response.ModalityContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.response.ModalityContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.mapper.IModalityContractTypeMapper;
import co.unicauca.digital.repository.back.domain.modalityContractType.model.ModalityContractType;
import co.unicauca.digital.repository.back.domain.modalityContractType.repository.IModalityContractTypeRepository;
import co.unicauca.digital.repository.back.domain.modalityContractType.service.IModalityContractTypeService;
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
public class ModalityContractTypeServiceImpl implements IModalityContractTypeService {

    /** Object to perform CRUD operations on the Product entity */
    private final IModalityContractTypeRepository modalityContractTypeRepository;

    /** Mapping object for mapping the products */
    private final IModalityContractTypeMapper modalityContractTypeMapper;

    /** Mapping object for mapping the products */
    private final IContractualDocumentMapper contractualDocumentMapper;

    /** Object to perform CRUD operations on the Modality entity */
    private final IModalityRepository modalityRepository;

    /** Object to perform CRUD operations on the ContractType entity */
    private final IContractTypeRepository contractTypeRepository;

    /** Object to perform CRUD operations on the InternalNormative entity */
    private final IInternalNormativeRepository internalNormativeRepository;

    /**
     * constructor method
     */
    public ModalityContractTypeServiceImpl(IModalityContractTypeRepository modalityContractTypeRepository, IModalityContractTypeMapper modalityContractTypeMapper, IContractualDocumentMapper contractualDocumentMapper, IModalityRepository modalityRepository, IContractTypeRepository contractTypeRepository, IInternalNormativeRepository internalNormativeRepository) {
        this.modalityContractTypeRepository = modalityContractTypeRepository;
        this.modalityContractTypeMapper = modalityContractTypeMapper;
        this.contractualDocumentMapper = contractualDocumentMapper;
        this.modalityRepository = modalityRepository;
        this.contractTypeRepository = contractTypeRepository;
        this.internalNormativeRepository = internalNormativeRepository;
    }

    /**
     * @see IModalityContractTypeService#createModalityContractType(ModalityContractTypeDtoCreateRequest)
     */
    @Override
    public Response<ModalityContractTypeDtoCreateResponse> createModalityContractType(ModalityContractTypeDtoCreateRequest modalityContractTypeDtoCreateRequest) {

        ModalityContractType modalityContractTypeModel = modalityContractTypeMapper.toEntityCreate(modalityContractTypeDtoCreateRequest);
        modalityContractTypeModel.setCreateTime(LocalDateTime.now());
        // TODO Set create user and get id from relationships

        //Set ContractType
        Optional<ContractType> contractType = contractTypeRepository.findById(modalityContractTypeDtoCreateRequest.getContractTypeId());
        if (contractType.isEmpty()) throw new BusinessRuleException("modalityContractType.contractType.association.error");
        modalityContractTypeModel.setContractType(contractType.get());

        //Set Modality
        Optional<Modality> modality = modalityRepository.findById(modalityContractTypeDtoCreateRequest.getModalityId());
        if (modality.isEmpty()) throw new BusinessRuleException("modalityContractType.modality.association.error");
        modalityContractTypeModel.setModality(modality.get());

        //Set InternalNormative
        Optional<InternalNormative> internalNormative = internalNormativeRepository.findById(modalityContractTypeDtoCreateRequest.getInternalNormativeId());
        if (internalNormative.isEmpty()) throw new BusinessRuleException("modalityContractType.internalNormative.association.error");
        modalityContractTypeModel.setInternalNormative(internalNormative.get());

        ModalityContractType modalityContractTypeSaved = modalityContractTypeRepository.save(modalityContractTypeModel);
        ModalityContractTypeDtoCreateResponse modalityContractTypeDtoCreateResponse = modalityContractTypeMapper.toDtoCreate(modalityContractTypeSaved);

        modalityContractTypeDtoCreateResponse.setModalityId(modalityContractTypeSaved.getModality().getId());
        modalityContractTypeDtoCreateResponse.setInternalNormativeId(modalityContractTypeSaved.getInternalNormative().getId());
        modalityContractTypeDtoCreateResponse.setContractTypeId(modalityContractTypeSaved.getContractType().getId());

        return new ResponseHandler<>(200, "Tipo de contrato por modalidad creado exitosamente", "Tipo de contrato por modalidad creado exitosamente", modalityContractTypeDtoCreateResponse).getResponse();
    }

    /**
     * @see IModalityContractTypeService#getById(Integer)
     */
    @Override
    public Response<ModalityContractTypeDtoFindResponse> getById(Integer id) {

        Optional<ModalityContractType> modalityContractTypeFound = modalityContractTypeRepository.findById(id);
        if (modalityContractTypeFound.isEmpty()) throw new BusinessRuleException("modalityContractType.request.not.found");
        ModalityContractTypeDtoFindResponse modalityContractTypeDtoFindResponse = modalityContractTypeMapper.toDtoFind(modalityContractTypeFound.get());

        // TODO Get id from relationships
        modalityContractTypeDtoFindResponse.setModalityId(modalityContractTypeFound.get().getModality().getId());
        modalityContractTypeDtoFindResponse.setInternalNormativeId(modalityContractTypeFound.get().getInternalNormative().getId());
        modalityContractTypeDtoFindResponse.setContractTypeId(modalityContractTypeFound.get().getContractType().getId());

        return new ResponseHandler<>(200, "Tipo de contrato por modalidad encontrado", "Tipo de contrato por modalidad encontrado", modalityContractTypeDtoFindResponse).getResponse();
    }

    /**
     * @see IModalityContractTypeService#getAll(int, int)
     */
    @Override
    public Response<PageableResponse<Object>> getAll(int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ModalityContractType> responsePage = modalityContractTypeRepository.findAll(pageRequest);

        // TODO Get id from relationships
        List<Object> modalityContractTypeDtoFindResponses = responsePage.get().map(
                modalityContractTypeMapper::toDtoFind
        ).collect(Collectors.toList());

        PageableResponse<Object> response = PageableResponse.builder()
                .data(modalityContractTypeDtoFindResponses)
                .pageNo(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .build();

        return new ResponseHandler<>(200, "Se retorno los registro de tipo de contrato por modalidad", "Se retorno los registro de tipo de contrato por modalidad", response).getResponse();
    }

    /**
     * @see IModalityContractTypeService#getByContractModality(Integer, Integer) (int, int)
     */
    @Override
    public Response<ModalityContractTypeDtoFindResponse> getByContractModality(Integer contractTypeId, Integer modalityId) {

        //ContractType
        Optional<ContractType> contractType = contractTypeRepository.findById(contractTypeId);
        if (contractType.isEmpty()) throw new BusinessRuleException("modalityContractType.contractType.association.error");

        //Modality
        Optional<Modality> modality = modalityRepository.findById(modalityId);
        if (modality.isEmpty()) throw new BusinessRuleException("modalityContractType.modality.association.error");

        Optional<ModalityContractType> modalityContractTypeFound = modalityContractTypeRepository.findByContractModality(contractTypeId, modalityId);

        if (modalityContractTypeFound.isEmpty()) throw new BusinessRuleException("modalityContractType.request.not.found");
        ModalityContractTypeDtoFindResponse modalityContractTypeDtoFindResponse = modalityContractTypeMapper.toDtoFind(modalityContractTypeFound.get());

        modalityContractTypeDtoFindResponse.setModalityId(modalityId);
        modalityContractTypeDtoFindResponse.setInternalNormativeId(modalityContractTypeFound.get().getInternalNormative().getId());
        modalityContractTypeDtoFindResponse.setContractTypeId(contractTypeId);

        return new ResponseHandler<>(200, "Tipo de contrato por modalidad encontrado", "Tipo de contrato por modalidad encontrado", modalityContractTypeDtoFindResponse).getResponse();
    }

    /**
     * @see IModalityContractTypeService#updateModalityContractType(ModalityContractTypeDtoUpdateRequest)
     */
    @Override
    public Response<ModalityContractTypeDtoCreateResponse> updateModalityContractType(ModalityContractTypeDtoUpdateRequest modalityContractTypeDtoUpdateRequest) {

        Optional<ModalityContractType> modalityContractType = modalityContractTypeRepository.findById(modalityContractTypeDtoUpdateRequest.getId());

        if (modalityContractType.isEmpty()) throw new BusinessRuleException("modalityContractType.request.not.found");

        ModalityContractType updateModalityContractType = ModalityContractType.builder()
                .id(modalityContractType.get().getId())
                .createUser(modalityContractType.get().getCreateUser())
                .createTime(modalityContractType.get().getCreateTime())
                .updateTime(LocalDateTime.now())
                // TODO .updateUser(XXX) and Set id from relationships
                .modality(modalityContractType.get().getModality())
                .contractType(modalityContractType.get().getContractType())
                .internalNormative(modalityContractType.get().getInternalNormative())
                .build();

        ModalityContractType modalityContractTypeSaved = modalityContractTypeRepository.save(updateModalityContractType);
        ModalityContractTypeDtoCreateResponse modalityContractTypeDtoCreateResponse = modalityContractTypeMapper.toDtoCreate(modalityContractTypeSaved);
        // TODO Get id from relationships
        modalityContractTypeDtoCreateResponse.setModalityId(modalityContractTypeSaved.getModality().getId());
        modalityContractTypeDtoCreateResponse.setInternalNormativeId(modalityContractTypeSaved.getInternalNormative().getId());
        modalityContractTypeDtoCreateResponse.setContractTypeId(modalityContractTypeSaved.getContractType().getId());

        return new ResponseHandler<>(200, "Tipo de modalidad por contrato actualizado exitosamente", "Tipo de modalidad por contrato actualizado exitosamente", modalityContractTypeDtoCreateResponse).getResponse();
    }

    /**
     * @see IModalityContractTypeService#deleteModalityContractType(Integer)
     */
    @Override
    public Response<Boolean> deleteModalityContractType(Integer id) {

        Optional<ModalityContractType> modalityContractType = modalityContractTypeRepository.findById(id);

        if (modalityContractType.isEmpty()) throw new BusinessRuleException("modalityContractType.request.not.found");
        modalityContractTypeRepository.deleteById(id);

        return new ResponseHandler<>(200, "Tipo de modalidad por contrato eliminado exitosamente", "Tipo de modalidad por contrato eliminado exitosamente", true).getResponse();
    }

    @Override
    public Response<List<ContractualDocumentDtoFindResponse>> getCheckListById(Integer id) {

        Optional<ModalityContractType> modalityContractType = this.modalityContractTypeRepository.findById(id);

        if(modalityContractType.isEmpty()) throw new BusinessRuleException("modalityContractType.request.not.found");

        if(modalityContractType.get().getContractualDocuments().isEmpty()){
            throw new BusinessRuleException("modalityContractType.contractualDocuments.not.found");
        }

        List<ContractualDocumentDtoFindResponse> contractualDocumentDtoFindResponses = modalityContractType.get().getContractualDocuments().stream()
                .map(this.contractualDocumentMapper::toDtoFind)
                .collect(Collectors.toList());

        return new ResponseHandler<>(200, "CheckList recuperada exitosamente", "CheckList recuperada exitosamente",contractualDocumentDtoFindResponses).getResponse();
    }


}
