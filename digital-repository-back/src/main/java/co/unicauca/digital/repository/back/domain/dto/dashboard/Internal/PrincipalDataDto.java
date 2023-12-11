package co.unicauca.digital.repository.back.domain.dto.dashboard.Internal;


import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class PrincipalDataDto {

    private String nombreSubCatContrato;
    private String rangoScore;
    private Integer score;
    private Integer cantidad;
    private List<String> idContratos;   
    
    public PrincipalDataDto(){
        nombreSubCatContrato=null;
        rangoScore=null;
        score=null;
        cantidad=0;
        idContratos=new ArrayList<String>();
    }
    public void addIdContrato(Integer idC){
        idContratos.add(idC.toString());
        //cantidad++;
    }
    public void deca(){
        nombreSubCatContrato=null;
        rangoScore=null;
        score=null;
        cantidad=0;
        idContratos=new ArrayList<String>();
    }

    public PrincipalDataDto(PrincipalDataDto principalDataDto){
        nombreSubCatContrato=principalDataDto.getNombreSubCatContrato();
        rangoScore=principalDataDto.getRangoScore();
        score=principalDataDto.getScore();
        cantidad=principalDataDto.getCantidad();
        idContratos=principalDataDto.getIdContratos();
    }
    
}
