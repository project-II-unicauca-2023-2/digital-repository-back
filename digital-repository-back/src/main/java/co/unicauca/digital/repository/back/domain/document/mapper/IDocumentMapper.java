package co.unicauca.digital.repository.back.domain.document.mapper;

import co.unicauca.digital.repository.back.domain.document.dto.request.DocumentDtoRequest;
import co.unicauca.digital.repository.back.domain.document.dto.response.DocumentDtoResponse;
import co.unicauca.digital.repository.back.domain.document.model.Document;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IDocumentMapper {

    IDocumentMapper INSTANCE = Mappers.getMapper(IDocumentMapper.class);
    @Mappings({
    })
    DocumentDtoResponse toDto(final Document document);
    List<DocumentDtoResponse> toDocumentList(List<Document> documentList);

    @InheritInverseConfiguration
    @Mappings({
    })
    Document toEntity(final DocumentDtoRequest requestDto);

}
