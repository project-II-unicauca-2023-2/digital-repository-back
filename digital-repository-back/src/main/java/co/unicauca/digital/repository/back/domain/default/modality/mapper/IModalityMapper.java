package co.unicauca.digital.repository.back.domain.modality.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import co.unicauca.digital.repository.back.domain.modality.dto.request.ModalityDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.modality.dto.response.ModalityDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.modality.dto.response.ModalityDtoFindResponse;
import co.unicauca.digital.repository.back.domain.modality.model.Modality;

/**
 * Interface that defines the mapper in charge of the O/R transition to a DTO object.
 */

@Mapper(componentModel = "Spring")
public interface IModalityMapper {
    @Mappings({
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "externalCode", source = "externalCode"),
        @Mapping(target = "createUser", source = "createUser"),
        @Mapping(target = "createTime", source = "createTime")
    })
    ModalityDtoCreateResponse toDtoCreate(final Modality modality);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "externalCode", source = "externalCode"),
    })
    ModalityDtoFindResponse toDtoFind(final Modality modality);

    @Mappings({
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "externalCode", source = "externalCode"),
    })
    Modality toEntityCreate(final ModalityDtoCreateRequest modalityDtoCreateRequest);
}
