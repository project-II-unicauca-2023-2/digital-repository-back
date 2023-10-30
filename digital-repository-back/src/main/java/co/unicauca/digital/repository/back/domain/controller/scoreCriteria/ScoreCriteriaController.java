package co.unicauca.digital.repository.back.domain.controller.scoreCriteria;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDtoResponse;
import co.unicauca.digital.repository.back.domain.service.scoreCriteria.IScoreCriteriaService;
import co.unicauca.digital.repository.back.global.response.Response;
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
    @GetMapping("scoreCriteriaDataByMask")
    public ResponseEntity<Response<ScoreCriteriaDtoResponse>> getScoreCriteriaDataByMask(@RequestParam String referenceMask){
        return new ResponseEntity<>(
        this.scoreCriteriaService.DataScoreCriteriaByMask(referenceMask),
        HttpStatus.OK);
    }
}
