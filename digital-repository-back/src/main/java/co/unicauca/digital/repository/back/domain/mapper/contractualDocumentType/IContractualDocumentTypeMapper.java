package co.unicauca.digital.repository.back.domain.mapper.contractualDocumentType;

import co.unicauca.digital.repository.back.domain.dto.contractualDocumentType.request.ContractualDocumentTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.contractualDocumentType.response.ContractualDocumentTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.contractualDocumentType.response.ContractualDocumentTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.model.contractualDocumentType.ContractualDocumentType;
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
        ContractualDocumentType toEntityCreate(
                        final ContractualDocumentTypeDtoCreateRequest contractualDocumentTypeDtoCreateRequest);
}
