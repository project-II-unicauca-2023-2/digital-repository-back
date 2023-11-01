package co.unicauca.digital.repository.back.domain.mapper.criteria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import co.unicauca.digital.repository.back.domain.dto.criteria.response.CriteriaDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.modality.response.ModalityDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.model.criteria.Criteria;
import co.unicauca.digital.repository.back.domain.model.modality.Modality;

/**
 * Interface that defines the mapper in charge of the O/R transition to a DTO
 * object.
 */

@Mapper(componentModel = "Spring")
public interface ICriteriaMapper {
    
    //Create criteria
    @Mappings({
            @Mapping(target = "criteriaType", source = "criteriaType"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "name", source = "name"),           
            
    })
    CriteriaDtoCreateResponse toDtoCreate(final Criteria criteria);

    //Find by id criteria
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "criteriaType", source = "criteriaType"),
            
    })
    CriteriaDtoCreateResponse toDtoFind(final Criteria criteria);

}
