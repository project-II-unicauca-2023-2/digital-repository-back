package co.unicauca.digital.repository.back.domain.mapper.contractualDocument;

import co.unicauca.digital.repository.back.domain.dto.contractualDocument.request.ContractualDocumentDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.contractualDocument.response.ContractualDocumentDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.contractualDocument.response.ContractualDocumentDtoFindResponse;
import co.unicauca.digital.repository.back.domain.model.contractualDocument.ContractualDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IContractualDocumentMapper {
        @Mappings({
                        @Mapping(target = "description", source = "description"),
                        @Mapping(target = "isRequired", source = "isRequired"),
                        @Mapping(target = "subdirectory", source = "subdirectory"),
                        @Mapping(target = "ordering", source = "ordering"),
                        @Mapping(target = "createTime", source = "createTime"),
                        @Mapping(target = "createUser", source = "createUser"),
                        @Mapping(target = "contractualDocumentType", source = "contractualDocumentType.id"),
                        @Mapping(target = "modalityContractType", source = "modalityContractType.id")
        })
        ContractualDocumentDtoCreateResponse toDtoCreate(final ContractualDocument contractualDocumentType);

        @Mappings({
                        @Mapping(target = "description", source = "description"),
                        @Mapping(target = "isRequired", source = "isRequired"),
                        @Mapping(target = "subdirectory", source = "subdirectory"),
                        @Mapping(target = "ordering", source = "ordering"),
                        @Mapping(target = "createUser", source = "createUser"),
                        @Mapping(target = "contractualDocumentType", ignore = true),
                        @Mapping(target = "modalityContractType", ignore = true)

        })
        ContractualDocument toEntityCreate(
                        final ContractualDocumentDtoCreateRequest contractualDocumentTypeDtoCreateRequest);

        @Mappings({
                        @Mapping(target = "id", source = "id"),
                        @Mapping(target = "description", source = "description"),
                        @Mapping(target = "isRequired", source = "isRequired"),
                        @Mapping(target = "subdirectory", source = "subdirectory"),
                        @Mapping(target = "ordering", source = "ordering"),
                        @Mapping(target = "createTime", source = "createTime"),
                        @Mapping(target = "createUser", source = "createUser"),
                        @Mapping(target = "contractualDocumentType", source = "contractualDocumentType"),
                        @Mapping(target = "modalityContractType", source = "modalityContractType.id")
        })
        ContractualDocumentDtoFindResponse toDtoFind(final ContractualDocument contractualDocument);
}
