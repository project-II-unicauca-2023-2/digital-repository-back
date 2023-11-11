package co.unicauca.digital.repository.back.domain.controller.Criteria;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.unicauca.digital.repository.back.domain.dto.aboutVendor.response.aboutVendorDto;
import co.unicauca.digital.repository.back.domain.dto.criteria.response.CriteriaDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.service.criteria.ICriteriaService;
import co.unicauca.digital.repository.back.domain.service.vendorType.IAboutVendorService;
import co.unicauca.digital.repository.back.global.response.Response;

@RestController
@RequestMapping("/criteria")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class CriteriaController {
    
    /** Object used to invoke the operations of the IModalityService interface */
    private final ICriteriaService criteriaService;

    /* Constructor */

    CriteriaController(ICriteriaService criteriaService) {
        this.criteriaService = criteriaService;
    }

     /**
     * API Method to get Criteria by ID
     * 
     * @param id Object ID
     * @return {@link Response} Response object for the service, which contains
     *         information about the outcome of the transaction.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response<CriteriaDtoCreateResponse>> getById(@Valid @PathVariable final Integer id) {
        return new ResponseEntity<>(this.criteriaService.getById(id), HttpStatus.OK);
    }

    
}
