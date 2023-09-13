package co.unicauca.digital.repository.back.domain.contractType.dto.response;
import lombok.*;

/**
 * Class that defines an entity for the O/R mapping for the output of information from the ContractType table.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContractTypeDtoFindResponse {

     /** ContractType ID */
     private Integer id;

     /** ContractType name */
     private String name;
 
     /** ContractType externalCode */
     private String externalCode;

     /** ContractType description */
     private String description;
}