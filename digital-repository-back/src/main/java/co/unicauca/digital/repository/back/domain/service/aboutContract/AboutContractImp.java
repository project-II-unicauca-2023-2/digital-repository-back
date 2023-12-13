package co.unicauca.digital.repository.back.domain.service.aboutContract;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.dto.aboutContract.response.aboutContractIdDTO;
import co.unicauca.digital.repository.back.domain.repository.contractType.IContractTypeRepository;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;

@Service
public class AboutContractImp implements iAboutContract{
    @Autowired
    IContractTypeRepository repo;

    @Override
    public Response<List<aboutContractIdDTO>> getContractsById(List<Integer> idContracts) {
        List<aboutContractIdDTO> ret = new ArrayList<aboutContractIdDTO>();
        for (Integer id : idContracts) {
            ret.add(repo.getMasksByIdContract(id));
        }
        return new ResponseHandler<>(200, "Procesado", "Procesado", ret).getResponse();
    }

    
}
