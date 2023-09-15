package co.unicauca.digital.repository.back.domain.mapper.collection;

import co.unicauca.digital.repository.back.domain.dto.collection.request.CollectionDtoRequest;
import co.unicauca.digital.repository.back.domain.dto.collection.response.CollectionDtoResponse;
import co.unicauca.digital.repository.back.domain.model.collection.Collection;
import co.unicauca.digital.repository.back.domain.dto.document.response.DocumentDtoResponse;
import co.unicauca.digital.repository.back.domain.mapper.document.IDocumentMapper;
import co.unicauca.digital.repository.back.domain.model.document.Document;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = IDocumentMapper.class)
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
    @Mapping(source = "contractId", target = "contract.id")
    Collection toEntity(final CollectionDtoRequest collectionDtoRequest);

}
