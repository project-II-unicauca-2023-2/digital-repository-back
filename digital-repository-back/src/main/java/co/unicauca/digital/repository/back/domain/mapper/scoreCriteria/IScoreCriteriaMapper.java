package co.unicauca.digital.repository.back.domain.mapper.scoreCriteria;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDto;
import co.unicauca.digital.repository.back.domain.dto.scoreCriteria.response.ScoreCriteriaDtoResponse;
import co.unicauca.digital.repository.back.domain.model.score.Score;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IScoreCriteriaMapper {
    @Mappings({
            // Score
            @Mapping(target = "totalScore", source = "score.totalScore"),
            // ScoreCriteria
            @Mapping(target = "listaScoreCriteria", source = "scoreCriteriaDtoList"),
    })
    ScoreCriteriaDtoResponse toScoreCriteriaDtoResponse(Score score, List<ScoreCriteriaDto> scoreCriteriaDtoList);
}
