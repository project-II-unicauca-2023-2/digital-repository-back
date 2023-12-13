package co.unicauca.digital.repository.back.domain.dto.vendor.response;

import java.util.List;

import lombok.*;
/**
 * Class that 
 */
@Getter
@Setter
@NoArgsConstructor


public class VendorDtoAboutData {

    private int idVendor;
    private String nameVendor;
    private String credential;
    private int numContract;
    private float scoreYear;
    private int numContractYear;
    private float scoreGeneral;
    private List<String> idsContract;


    public VendorDtoAboutData(int idVendor,String nameVendor, String credential,int numContract,float scoreYear,int numContractYear,float scoreGeneral){
    
        this.idVendor = idVendor;
        this.nameVendor = nameVendor;
        this.credential = credential;
        this.numContract = numContract;
        this.scoreYear = scoreYear;
        this.numContractYear = numContractYear;
        this.scoreGeneral = scoreGeneral;
    }
}
