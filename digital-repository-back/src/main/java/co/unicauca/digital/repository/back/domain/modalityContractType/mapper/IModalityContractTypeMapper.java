package co.unicauca.digital.repository.back.domain.modalityContractType.mapper;

import co.unicauca.digital.repository.back.domain.modalityContractType.dto.request.ModalityContractTypeDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.response.ModalityContractTypeDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.dto.response.ModalityContractTypeDtoFindResponse;
import co.unicauca.digital.repository.back.domain.modalityContractType.model.ModalityContractType;
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
    ModalityContractType toEntityCreate(final ModalityContractTypeDtoCreateRequest modalityContractTypeDtoCreateRequest);
}
