package co.unicauca.digital.repository.back.domain.controller.dashBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import co.unicauca.digital.repository.back.domain.dto.dashboard.response.AllPrincipalDataDto;
import co.unicauca.digital.repository.back.domain.service.dashBoard.IDashBoardService;
import co.unicauca.digital.repository.back.global.response.Response;

@RestController
@RequestMapping("/dashBoard")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class DashBoardController {
    
    @Autowired
    private IDashBoardService dash;

    @GetMapping("/p")
    public Integer prueba (){
        return dash.prueba();
    }

    
    @GetMapping("/Bienes/{year}")
    public ResponseEntity<Response<AllPrincipalDataDto>> Bienes (@PathVariable Integer year){
        return new ResponseEntity<>(dash.getDatasByDesc("Bienes", year), HttpStatus.OK);
    }
     @GetMapping("/Servicios/{year}")
    public ResponseEntity<Response<AllPrincipalDataDto>> Servicios (@PathVariable Integer year){
        return new ResponseEntity<>(dash.getDatasByDesc("Servicios", year), HttpStatus.OK);
    }
     @GetMapping("/Obras/{year}")
    public ResponseEntity<Response<AllPrincipalDataDto>> Obras (@PathVariable Integer year){
        return new ResponseEntity<>(dash.getDatasByDesc("Obras", year), HttpStatus.OK);
    }
}
