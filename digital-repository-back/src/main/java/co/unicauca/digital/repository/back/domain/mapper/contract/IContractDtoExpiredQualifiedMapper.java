package co.unicauca.digital.repository.back.domain.mapper.contract;

import co.unicauca.digital.repository.back.domain.dto.collection.response.CollectionDtoResponse;
import co.unicauca.digital.repository.back.domain.mapper.collection.ICollectionMapper;
import co.unicauca.digital.repository.back.domain.model.collection.Collection;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoExpiredQualifiedResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindContractualFoldersResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractVendorDtoResponse;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;
import co.unicauca.digital.repository.back.domain.model.modality.Modality;
import co.unicauca.digital.repository.back.domain.model.score.Score;
import co.unicauca.digital.repository.back.domain.model.vendor.Vendor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that defines the mapper in charge of the O/R transition to a DTO
 * object.
 */
@Mapper(componentModel = "spring", uses = ICollectionMapper.class)
public interface IContractDtoExpiredQualifiedMapper  {
        @Mappings({
            // Contract map
            @Mapping(target = "initialDate", source = "contract.initialDate"),
            @Mapping(target = "finalDate", source = "contract.finalDate"),
            @Mapping(target = "id", source = "contract.id"),
            @Mapping(target = "reference", source = "contract.reference"),
            // Vendor map
            @Mapping(target = "vendorId", source = "vendor.id"),
            //ContractType map
            @Mapping(target = "contractType", source = "contractType.name"),
             //Modality map
            @Mapping(target = "modality", source = "modality.name"),
            //Score map
            @Mapping(target = "scoreTotal", source = "score.totalScore")
        })
        ContractDtoExpiredQualifiedResponse toContractExpiredQualifiedResponse(Contract contract, Vendor vendor , ContractType contractType, Modality modality, Score score); 
}
