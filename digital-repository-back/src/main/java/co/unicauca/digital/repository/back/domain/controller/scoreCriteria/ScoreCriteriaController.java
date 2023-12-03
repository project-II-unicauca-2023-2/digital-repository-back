package co.unicauca.digital.repository.back.domain.controller.scoreCriteria;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoIdRequest;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.request.ScoreCriteriaDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaCalificationDomainDtoResponse;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDtoResponse;
import co.unicauca.digital.repository.back.domain.service.scoreCriteria.IScoreCriteriaService;
import co.unicauca.digital.repository.back.global.response.Response;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scoreCriteria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScoreCriteriaController {

    private final IScoreCriteriaService scoreCriteriaService;

    public ScoreCriteriaController(IScoreCriteriaService scoreCriteriaService) {
        this.scoreCriteriaService = scoreCriteriaService;
    }
    @PostMapping("scoreCriteriaDataByMask")
    public ResponseEntity<Response<ScoreCriteriaDtoResponse>> getScoreCriteriaDataByMask(
        @Valid @RequestBody final ContractDtoIdRequest contractDtoIdRequest
    ){
        return new ResponseEntity<>(
        this.scoreCriteriaService.DataScoreCriteriaByMask(contractDtoIdRequest),
        HttpStatus.OK);
    }

    @GetMapping("calificationDomain")
    public ResponseEntity<Response<ScoreCriteriaCalificationDomainDtoResponse>> getCalificationDomain(){
        return new ResponseEntity<>(
        this.scoreCriteriaService.CalificationDomain(),
        HttpStatus.OK);
    }

    @PostMapping("registerCalification")
    public ResponseEntity<Response<Boolean>> registerCalification(@Valid @RequestBody final ScoreCriteriaDtoCreateRequest calificationRequest){
        return new ResponseEntity<>(
        this.scoreCriteriaService.RegisterCalification(calificationRequest),
        HttpStatus.OK);
    }
}
