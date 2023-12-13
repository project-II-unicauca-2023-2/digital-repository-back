package co.unicauca.digital.repository.back.domain.service.aboutContract;

import java.util.List;

import co.unicauca.digital.repository.back.domain.dto.aboutContract.response.aboutContractIdDTO;
import co.unicauca.digital.repository.back.global.response.Response;

public interface iAboutContract {
    Response<List<aboutContractIdDTO>> getContractsById(List<Integer> idContracts);
    
}
