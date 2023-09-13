package co.unicauca.digital.repository.back.domain.modality.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.modality.dto.request.ModalityDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.modality.dto.request.ModalityDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.modality.dto.response.ModalityDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.modality.dto.response.ModalityDtoFindResponse;
import co.unicauca.digital.repository.back.domain.modality.mapper.IModalityMapper;
import co.unicauca.digital.repository.back.domain.modality.model.Modality;
import co.unicauca.digital.repository.back.domain.modality.repository.IModalityRepository;
import co.unicauca.digital.repository.back.domain.modality.service.IModalityService;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;


/** Class in charge of implementing the IModalityService interface {@link IModalityService} */
@Service
@Primary
public class ModalityServiceImpl implements IModalityService{
    
    /** Object to perform CRUD operations on the Modality entity */
    private final IModalityRepository modalityRepository;

    /** Mapping object for mapping the modalities */
    private final IModalityMapper modalityMapper;


    /* Constructor */
    ModalityServiceImpl(IModalityRepository modalityRepository, IModalityMapper modalityMapper){
        this.modalityRepository = modalityRepository;
        this.modalityMapper = modalityMapper;
    }

    /**
     * @see IModalityService#getById(int)
     */
    @Override
    public Response<ModalityDtoFindResponse> getById(int id) {
        Optional<Modality> modalityFound = this.modalityRepository.findById(id);

        if(modalityFound.isEmpty()) throw new BusinessRuleException("modality.request.not.found");

        ModalityDtoFindResponse modalityDtoFindResponse = modalityMapper.toDtoFind(modalityFound.get());

        return new ResponseHandler<>(200, "Encontrado", "Encontrado", modalityDtoFindResponse).getResponse();
        
    }

    /**
     * @see IModalityService#getAll(int, int)
     */
    @Override
    public Response<PageableResponse<Object>> getAll(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Modality> responsePage = modalityRepository.findAll(pageRequest);
        
        List<Object> modalityDtoFindResponses = responsePage.get().map(
            modalityMapper::toDtoFind
        ).collect(Collectors.toList());

        PageableResponse<Object> response = PageableResponse.builder()
        .data(modalityDtoFindResponses)
        .pageNo(responsePage.getNumber())
        .pageSize(responsePage.getSize())
        .totalElements(responsePage.getTotalElements())
        .totalPages(responsePage.getTotalPages())
        .last(responsePage.isLast())
        .build();

        return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();

    }


    /**
     * @see IModalityService#createModality(ModalityDtoCreateRequest)
     */
    @Override
    public Response<ModalityDtoCreateResponse> createModality(ModalityDtoCreateRequest modalityDtoCreateRequest) {
        if(entityExistsByExternalCode(modalityDtoCreateRequest.getExternalCode())) throw new BusinessRuleException("modality.request.already.exists");

        Modality modality = modalityMapper.toEntityCreate(modalityDtoCreateRequest);
        modality.setCreateTime(LocalDateTime.now());
        //TODO: Set Create User

        Modality modalitySaved = this.modalityRepository.save(modality);
        ModalityDtoCreateResponse modalityDtoCreateResponse = modalityMapper.toDtoCreate(modalitySaved);

        return new ResponseHandler<>(200, "Modalidad creada exitosamente", "Modalidad creada exitosamente", modalityDtoCreateResponse).getResponse();
    }


    /**
     * @see IModalityService#updateModality(ModalityDtoUpdateRequest)
     */
    @Override
    public Response<ModalityDtoCreateResponse> updateModality(ModalityDtoUpdateRequest modalityDtoUpdateRequest) {
        Optional<Modality> modality = modalityRepository.findById(modalityDtoUpdateRequest.getId());

        if (modality.isEmpty()) throw new BusinessRuleException("modality.request.not.found");

        Modality updateVendor = Modality.builder()
                .id(modality.get().getId())
                .name(modalityDtoUpdateRequest.getName())
                .externalCode(modalityDtoUpdateRequest.getExternalCode())
                .createUser(modality.get().getCreateUser())
                .createTime(modality.get().getCreateTime())
                .updateTime(LocalDateTime.now())
                .updateUser(modality.get().getUpdateUser())
                .build();

        Modality modalitySaved = this.modalityRepository.save(updateVendor);
        ModalityDtoCreateResponse modalityDtoCreateResponse = modalityMapper.toDtoCreate(modalitySaved);

        return new ResponseHandler<>(200, "Modalidad actualizada exitosamente", "Modalidad actualizada exitosamente", modalityDtoCreateResponse).getResponse();
    }


    /**
     * @see IModalityService#deleteModality(int)
     */
    @Override
    public Response<Boolean> deleteModality(int id) {
        Optional<Modality> modality = modalityRepository.findById(id);

        if (modality.isEmpty()) throw new BusinessRuleException("modality.request.not.found");
        modalityRepository.deleteById(id);

        return new ResponseHandler<>(200, "Modalidad eliminada exitosamente", "Modalidad eliminada exitosamente", !modalityRepository.existsById(id)).getResponse();
    }

    /**
     * Check if the ModalityDtoRequest already exists
     *
     * @param externalCode the request to be validated
     * @return true if the entity exists
     */
    private boolean entityExistsByExternalCode(final String externalCode) {
        return modalityRepository.findByExternalCode(externalCode).isPresent();
    }


}
