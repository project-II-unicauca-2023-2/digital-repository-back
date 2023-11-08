package co.unicauca.digital.repository.back.domain.service.scoreCriteria.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.request.ScoreCriteriaDtoCreate;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.request.ScoreCriteriaDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaCalificationDomainDto;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaCalificationDomainDtoResponse;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDto;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDtoResponse;
import co.unicauca.digital.repository.back.domain.mapper.scoreCriteria.IScoreCriteriaDtoMapper;
import co.unicauca.digital.repository.back.domain.mapper.scoreCriteria.IScoreCriteriaMapper;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.criteria.Criteria;
import co.unicauca.digital.repository.back.domain.model.score.Score;
import co.unicauca.digital.repository.back.domain.model.scoreCriteria.ScoreCriteria;
import co.unicauca.digital.repository.back.domain.repository.contract.IContractRepository;
import co.unicauca.digital.repository.back.domain.repository.criteria.ICriteriaRepository;
import co.unicauca.digital.repository.back.domain.repository.score.IScoreRepository;
import co.unicauca.digital.repository.back.domain.repository.scoreCriteria.IScoreCriteriaRepository;
import co.unicauca.digital.repository.back.domain.service.scoreCriteria.IScoreCriteriaService;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;

@Service
@Primary
public class ScoreCriteriaServiceImpl implements IScoreCriteriaService {
    /** Object to perform CRUD operations on the Product entity */
    private final IContractRepository contractRepository;
    private final IScoreCriteriaMapper scoreCriteriaMapper;
    private final IScoreCriteriaDtoMapper scoreCriteriaDtoMapper;
    private final IScoreCriteriaRepository scoreCriteriaRepository;
    private final ICriteriaRepository criteriaRepository;
    private final IScoreRepository scoreRepository;

    public ScoreCriteriaServiceImpl(IContractRepository contractRepository,IScoreCriteriaMapper scoreCriteriaMapper,
    IScoreCriteriaDtoMapper scoreCriteriaDtoMapper,IScoreCriteriaRepository scoreCriteriaRepository,ICriteriaRepository criteriaRepository,
    IScoreRepository scoreRepository){
        this.contractRepository = contractRepository;
        this.scoreCriteriaMapper = scoreCriteriaMapper;
        this.scoreCriteriaDtoMapper = scoreCriteriaDtoMapper;
        this.scoreCriteriaRepository = scoreCriteriaRepository;
        this.criteriaRepository = criteriaRepository;
        this.scoreRepository = scoreRepository;
    }
    public Response<ScoreCriteriaDtoResponse> DataScoreCriteriaByMask(String prmMask){
        Optional<Contract> contract = contractRepository.findByReference(prmMask);
        Contract objContract = contract.orElseThrow(() -> new BusinessRuleException("contract.request.not.found"));
        Score objScore = objContract.getScore();
        List<ScoreCriteria> objListScoreCriteria = objScore.getScoringCriteria();
        List<ScoreCriteriaDto> objListScoreCriteriaDto = new ArrayList<>();

        for (ScoreCriteria scoreCriteria : objListScoreCriteria) {
            objListScoreCriteriaDto.add(scoreCriteriaDtoMapper.toScoreCriteriaDto(scoreCriteria.getCriteria(), scoreCriteria));
        }
        return new ResponseHandler<>(200,"Nombre criterios, valores y total","Nombre criterios, valores y total",
           scoreCriteriaMapper.toScoreCriteriaDtoResponse(objScore, objListScoreCriteriaDto)).getResponse();  
    }

    public Response<ScoreCriteriaCalificationDomainDtoResponse> CalificationDomain(){
        /**
         * Por el momento se deja esta lista aqui, en el esquema actual de BD no se ve reflejado
         * campos que coincidan con esta lista.
         * Porque no dejar esta lista quemada en el front?
        */
        List<ScoreCriteriaCalificationDomainDto> calificationList = Arrays.asList(
        new ScoreCriteriaCalificationDomainDto("No cumple", 1),
        new ScoreCriteriaCalificationDomainDto("Mínimamente", 2),
        new ScoreCriteriaCalificationDomainDto("Parcialmente", 3),
        new ScoreCriteriaCalificationDomainDto("Plenamente", 4),
        new ScoreCriteriaCalificationDomainDto("Supera expectativas", 5)
        );
        return new ResponseHandler<>(200,"Dominios de calificación","Dominios de calificación",
        new ScoreCriteriaCalificationDomainDtoResponse(calificationList)).getResponse(); 
    }

    public Response<Boolean> RegisterCalification(ScoreCriteriaDtoCreateRequest calificationRequest){
        Optional<Contract> contract = contractRepository.findByReference(calificationRequest.getContractMask());
        Contract objContract = contract.orElseThrow(() -> new BusinessRuleException("contract.request.not.found"));
        Score objScore = objContract.getScore();
        if(isAlreadyRated(objScore)){
            return new ResponseHandler<>(200,"Ya existe una evaluacion registrada para el contrato asociado a la mascara solicitada","Ya existe una evaluacion registrada para el contrato asociado a la mascara solicitada",
            false).getResponse();
        }
        List<ScoreCriteriaDtoCreate> listCriteriaRate = calificationRequest.getListCriteriaRate();
        LocalDateTime currentDate = LocalDateTime.now();

        listCriteriaRate.forEach(item -> {
            Criteria objCriteria = criteriaRepository.findById(item.getCriteriaId())
                    .orElseThrow(() -> new BusinessRuleException("criteria.request.not.found"));
        
            scoreCriteriaRepository.save(new ScoreCriteria(
                    null,
                    item.getRate(),
                    currentDate,
                    currentDate,
                    objScore,
                    objCriteria
            ));
        });
        objScore.setTotalScore(GetAverageRate(listCriteriaRate));
        objScore.setUpdateTime(currentDate);
        this.scoreRepository.save(objScore);
        return new ResponseHandler<>(200,"Calificación registrada con éxito","Calificación registrada con éxito",
        true).getResponse(); 
    }
    private float GetAverageRate(List<ScoreCriteriaDtoCreate> prmListCriteriaRate){
        float sumOfRates = (float) prmListCriteriaRate.stream().mapToDouble(ScoreCriteriaDtoCreate::getRate).sum();
        return sumOfRates / prmListCriteriaRate.size();
    }
    private boolean isAlreadyRated(Score prmScore){
        return prmScore.getCreateTime().equals(prmScore.getUpdateTime()) ? false : true;
    }
}
