package co.unicauca.digital.repository.back.domain.mapper.contractType;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import co.unicauca.digital.repository.back.domain.dto.contractType.request.ContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.contractType.response.ContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.contractType.response.ContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;

/**
 * Interface that defines the mapper in charge of the O/R transition to a DTO
 * object.
 */
@Mapper(componentModel = "Spring")
public interface IContractTypeMapper {
    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "externalCode", source = "externalCode"),
            @Mapping(target = "createUser", source = "createUser"),
            @Mapping(target = "createTime", source = "createTime")
    })
    ContractTypeDtoCreateResponse toDtoCreate(final ContractType contractType);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "externalCode", source = "externalCode"),
    })
    ContractTypeDtoFindResponse toDtoFind(final ContractType contractType);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "externalCode", source = "externalCode"),
    })
    ContractType toEntityCreate(final ContractTypeDtoCreateRequest contractDtoCreateRequest);
}