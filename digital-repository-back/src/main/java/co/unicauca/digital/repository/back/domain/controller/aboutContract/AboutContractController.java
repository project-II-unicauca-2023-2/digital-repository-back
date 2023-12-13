package co.unicauca.digital.repository.back.domain.controller.aboutContract;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import co.unicauca.digital.repository.back.domain.dto.aboutContract.response.aboutContractIdDTO;
import co.unicauca.digital.repository.back.domain.service.aboutContract.AboutContractImp;
import co.unicauca.digital.repository.back.global.response.Response;

@RestController
@RequestMapping("/dashBoard")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class AboutContractController {
    @Autowired
    AboutContractImp conService;

    @PostMapping("/aboutContracts")
    public ResponseEntity<Response<List<aboutContractIdDTO>>> postMethodName(@RequestBody List<Integer> idContracts) {
        return new ResponseEntity<>(conService.getContractsById(idContracts), HttpStatus.OK);
    }
    
}
