package co.unicauca.digital.repository.back.domain.service.scoreCriteria.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDto;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDtoResponse;
import co.unicauca.digital.repository.back.domain.mapper.scoreCriteria.IScoreCriteriaDtoMapper;
import co.unicauca.digital.repository.back.domain.mapper.scoreCriteria.IScoreCriteriaMapper;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.score.Score;
import co.unicauca.digital.repository.back.domain.model.scoreCriteria.ScoreCriteria;
import co.unicauca.digital.repository.back.domain.repository.contract.IContractRepository;
import co.unicauca.digital.repository.back.domain.repository.criteria.ICriteriaRepository;
import co.unicauca.digital.repository.back.domain.repository.score.IScoreRepository;
import co.unicauca.digital.repository.back.domain.repository.scoreCriteria.IScoreCriteriaRepository;
import co.unicauca.digital.repository.back.domain.service.contract.IContractService;
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

    public ScoreCriteriaServiceImpl(IContractRepository contractRepository,IScoreCriteriaMapper scoreCriteriaMapper,
    IScoreCriteriaDtoMapper scoreCriteriaDtoMapper){
        this.contractRepository = contractRepository;
        this.scoreCriteriaMapper = scoreCriteriaMapper;
        this.scoreCriteriaDtoMapper = scoreCriteriaDtoMapper;
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
}
