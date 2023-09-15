package co.unicauca.digital.repository.back.domain.mapper.modalityContractType;

import co.unicauca.digital.repository.back.domain.dto.modalityContractType.request.ModalityContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.modalityContractType.response.ModalityContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.modalityContractType.response.ModalityContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.model.modalityContractType.ModalityContractType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IModalityContractTypeMapper {

        @Mappings({
                        @Mapping(target = "createUser", source = "createUser"),
                        @Mapping(target = "createTime", source = "createTime")
        })
        ModalityContractTypeDtoCreateResponse toDtoCreate(final ModalityContractType modalityContractType);

        @Mappings({
                        @Mapping(target = "id", source = "id"),
        })
        ModalityContractTypeDtoFindResponse toDtoFind(final ModalityContractType modalityContractType);

        @Mappings({
        })
        ModalityContractType toEntityCreate(
                        final ModalityContractTypeDtoCreateRequest modalityContractTypeDtoCreateRequest);
}
