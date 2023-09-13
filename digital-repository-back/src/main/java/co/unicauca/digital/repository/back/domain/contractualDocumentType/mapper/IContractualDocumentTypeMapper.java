package co.unicauca.digital.repository.back.domain.contractualDocumentType.mapper;

import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.request.ContractualDocumentTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response.ContractualDocumentTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.dto.response.ContractualDocumentTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.contractualDocumentType.model.ContractualDocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IContractualDocumentTypeMapper {

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "createTime", source = "createTime"),
            @Mapping(target = "createUser", source = "createUser")
    })
    ContractualDocumentTypeDtoCreateResponse toDtoCreate(final ContractualDocumentType contractualDocumentType);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "createTime", source = "createTime"),
            @Mapping(target = "createUser", source = "createUser")
    })
    ContractualDocumentTypeDtoFindResponse toDtoFind(final ContractualDocumentType contractualDocumentType);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "createUser", source = "createUser")
    })
    ContractualDocumentType toEntityCreate(final ContractualDocumentTypeDtoCreateRequest contractualDocumentTypeDtoCreateRequest);
}
