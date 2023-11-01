package co.unicauca.digital.repository.back.domain.service.criteria.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.dto.criteria.request.CriteriaDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.criteria.response.CriteriaDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.modality.request.ModalityDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.modality.response.ModalityDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.modality.response.ModalityDtoFindResponse;
import co.unicauca.digital.repository.back.domain.mapper.criteria.ICriteriaMapper;
import co.unicauca.digital.repository.back.domain.model.criteria.Criteria;
import co.unicauca.digital.repository.back.domain.model.modality.Modality;
import co.unicauca.digital.repository.back.domain.repository.criteria.ICriteriaRepository;
import co.unicauca.digital.repository.back.domain.service.criteria.ICriteriaService;
import co.unicauca.digital.repository.back.domain.service.modality.IModalityService;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;


/**
 * Class in charge of implementing the ICriteriaService interface
 * {@link ICriteriaService}
 */
@Service
@Primary
public class CriteriaServiceImpl implements ICriteriaService{

    /** Object to perform CRUD operations on the Criteria entity */
    private final ICriteriaRepository criteriaRepository;

    /** Mapping object for mapping the criteria */
    private final ICriteriaMapper criteriaMapper;

    /* Constructor */
    CriteriaServiceImpl(ICriteriaRepository criteriaRepository, ICriteriaMapper criteriaMapper) {
        this.criteriaRepository = criteriaRepository;
        this.criteriaMapper = criteriaMapper;
    }

    @Override
    public Response<CriteriaDtoCreateResponse> getById(int id) {
         Optional<Criteria> criteriaFound = this.criteriaRepository.findById(id);

        if (criteriaFound.isEmpty())
            throw new BusinessRuleException("criteria.request.not.found");

        CriteriaDtoCreateResponse criteriaDtoCreateResponse = criteriaMapper.toDtoFind(criteriaFound.get());

        return new ResponseHandler<>(200, "Encontrado", "Encontrado", criteriaDtoCreateResponse).getResponse();
    }


    
}
