package co.unicauca.digital.repository.back.domain.dto.dashboard.response;

import java.util.ArrayList;
import java.util.List;

import co.unicauca.digital.repository.back.domain.dto.dashboard.Internal.PrincipalDataDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class AllPrincipalDataDto {

    
    private List<PrincipalDataDto> datosDashBoardPrincipal;

    public AllPrincipalDataDto(){
        datosDashBoardPrincipal= new ArrayList<PrincipalDataDto>();
    }

    public void addData(PrincipalDataDto data){
        datosDashBoardPrincipal.add(data);
    }
    
}
