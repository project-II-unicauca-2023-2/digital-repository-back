package co.unicauca.digital.repository.back.domain.collection.mapper;

import co.unicauca.digital.repository.back.domain.collection.dto.request.CollectionDtoRequest;
import co.unicauca.digital.repository.back.domain.collection.dto.response.CollectionDtoResponse;
import co.unicauca.digital.repository.back.domain.collection.model.Collection;
import co.unicauca.digital.repository.back.domain.document.dto.response.DocumentDtoResponse;
import co.unicauca.digital.repository.back.domain.document.mapper.IDocumentMapper;
import co.unicauca.digital.repository.back.domain.document.model.Document;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = IDocumentMapper.class )
public interface ICollectionMapper {

    ICollectionMapper INSTANCE = Mappers.getMapper(ICollectionMapper.class);
    @Mappings({
            @Mapping(source = "contract.id", target = "contractId"),
            @Mapping(target = "documents", qualifiedByName = "MapDocuments"),
            @Mapping(source = "contractualDocument.id", target = "contractualDocumentId")
    })
    CollectionDtoResponse toDto(Collection collection);

    @Named("MapDocuments")
    default List<DocumentDtoResponse> mapDocuments(List<Document> documents) {
        if (documents == null) {
            return null;
        }
        return documents.stream()
                .map(IDocumentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
    List<CollectionDtoResponse> toCollectionList(List<Collection> collectionList);

    @InheritInverseConfiguration
    @Mapping(source = "contractId",target = "contract.id" )
    Collection toEntity(final CollectionDtoRequest collectionDtoRequest);

}
