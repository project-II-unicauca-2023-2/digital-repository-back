package co.unicauca.digital.repository.back.domain.service.dashBoard;

import co.unicauca.digital.repository.back.domain.dto.dashboard.response.AllPrincipalDataDto;
import co.unicauca.digital.repository.back.global.response.Response;

public interface IDashBoardService {

    Response<AllPrincipalDataDto> getDatasByDesc (String desc, Integer year);

    Integer prueba();

    

}
