package co.unicauca.digital.repository.back.domain.contract.mapper;


import co.unicauca.digital.repository.back.domain.contract.dto.response.ContractDtoFindContractualFoldersResponse;
import co.unicauca.digital.repository.back.domain.contract.model.ContractDtoFindContractualFolders;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * Class that defines the mapper in charge of the O/R transition to a DTO object.
 */
@Mapper(componentModel = "spring")
public interface IContractDtoFindContractualFoldersMapper {
    @Mappings({

    })
    ContractDtoFindContractualFoldersResponse toDtoFind(final ContractDtoFindContractualFolders contract);


}
