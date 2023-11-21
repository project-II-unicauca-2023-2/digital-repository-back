package co.unicauca.digital.repository.back.domain.mapper.scoreCriteria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDto;
import co.unicauca.digital.repository.back.domain.model.criteria.Criteria;
import co.unicauca.digital.repository.back.domain.model.scoreCriteria.ScoreCriteria;

@Mapper(componentModel = "spring")
public interface IScoreCriteriaDtoMapper{
    @Mappings({
            // Score
            @Mapping(target = "name", source = "criteria.name"),
            // ScoreCriteria
            @Mapping(target = "rate", source = "scoreCriteria.rate"),
    })
    ScoreCriteriaDto toScoreCriteriaDto(Criteria criteria, ScoreCriteria scoreCriteria);
}
