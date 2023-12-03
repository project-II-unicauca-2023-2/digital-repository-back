package co.unicauca.digital.repository.back.domain.service.dashBoard;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.unicauca.digital.repository.back.domain.dto.dashboard.Internal.DataSave;
import co.unicauca.digital.repository.back.domain.dto.dashboard.Internal.PrincipalDataDto;
import co.unicauca.digital.repository.back.domain.dto.dashboard.response.AllPrincipalDataDto;
import co.unicauca.digital.repository.back.domain.repository.dashBoard.IDashRepo;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;

@Service
public class DashBoardService implements IDashBoardService {
    @Autowired 
    IDashRepo dashRepo;

     
       
    @Override
    public Integer prueba (){
        
     return 0;
     }
    


    @Override
    public Response<AllPrincipalDataDto> getDatasByDesc(String desc, Integer year) {
        AllPrincipalDataDto allReturn = new AllPrincipalDataDto();
        PrincipalDataDto principal = new PrincipalDataDto();
        // 1,1.9 ""No Cumple[1,1.9]" 
        List<DataSave> datas=dashRepo.getDataByDes(desc,1f,1.9f, year);
        Map<String, List<DataSave>> madata = datas.stream().collect(Collectors.groupingBy(DataSave::getName));
        madata.forEach((name, dataSave) -> {
            principal.deca();
            principal.setNombreSubCatContrato(name);
            principal.setRangoScore("No Cumple[1, 1.9]");
            principal.setScore(1);
            principal.setCantidad(dataSave.size());
            for (DataSave dataSave2 : dataSave) {
                principal.addIdContrato(dataSave2.getId());
            }
            allReturn.addData(new PrincipalDataDto(principal));
            
        }); 
        //2, 2.9 "" "Minimamente[2,2.9]" 
        datas=dashRepo.getDataByDes(desc,2f,2.9f, year);
        madata = datas.stream().collect(Collectors.groupingBy(DataSave::getName));
        madata.forEach((name, dataSave) -> {
            principal.deca();
            principal.setNombreSubCatContrato(name);
            principal.setRangoScore("Mínimamente[2, 2.9]");
            principal.setScore(2);
            principal.setCantidad(dataSave.size());
            for (DataSave dataSave2 : dataSave) {
                principal.addIdContrato(dataSave2.getId());
            }
            allReturn.addData(new PrincipalDataDto(principal));
        });
        

        //3,3.9 """Parcialmente[3,3.9]" 
        datas=dashRepo.getDataByDes(desc,3f,3.9f, year);
        madata = datas.stream().collect(Collectors.groupingBy(DataSave::getName));
        madata.forEach((name, dataSave) -> {
            principal.deca();
            principal.setNombreSubCatContrato(name);
            principal.setRangoScore("Parcialmente[3, 3.9]");
            principal.setScore(3);
            principal.setCantidad(dataSave.size());
            for (DataSave dataSave2 : dataSave) {
                principal.addIdContrato(dataSave2.getId());
            }
            allReturn.addData(new PrincipalDataDto(principal));
        });
        
        //4,4.9 "" "Plenante[4,4.9]" 
        datas=dashRepo.getDataByDes(desc,4f,4.9f, year);
        madata = datas.stream().collect(Collectors.groupingBy(DataSave::getName));
        madata.forEach((name, dataSave) -> {
            principal.deca();
            principal.setNombreSubCatContrato(name);
            principal.setRangoScore("Plenamente[4, 4.9]");
            principal.setScore(4);
            principal.setCantidad(dataSave.size());
            for (DataSave dataSave2 : dataSave) {
                principal.addIdContrato(dataSave2.getId());
            }
            allReturn.addData(new PrincipalDataDto(principal));
        });

        //5 "SuperaEspectativas[5]"
        datas=dashRepo.getDataByDes(desc,5f,5.9f, year);
        madata = datas.stream().collect(Collectors.groupingBy(DataSave::getName));
        madata.forEach((name, dataSave) -> {
            principal.deca();
            principal.setNombreSubCatContrato(name);
            principal.setRangoScore("Plenamente[5]");
            principal.setScore(5);
            principal.setCantidad(dataSave.size());
            for (DataSave dataSave2 : dataSave) {
                principal.addIdContrato(dataSave2.getId());
            }
            allReturn.addData(new PrincipalDataDto(principal));
        });
        return new ResponseHandler<>(200, "Procesado", "Procesado", allReturn).getResponse();
    }
    
    
}
