package co.unicauca.digital.repository.back.domain.mapper.contract;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractVendorDtoResponse;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.vendor.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IContractVendorMapper {

    @Mappings({
            // Contract map
            @Mapping(target = "initialDate", source = "contract.initialDate"),
            @Mapping(target = "finalDate", source = "contract.finalDate"),
            @Mapping(target = "subject", source = "contract.subject"),
            // Vendor map
            @Mapping(target = "name", source = "vendor.name"),
            @Mapping(target = "identification", source = "vendor.identification")
    })
    ContractVendorDtoResponse toContractVendorDtoResponse(Contract contract, Vendor vendor);
}