package co.unicauca.digital.repository.back.domain.vendor.mapper;


import co.unicauca.digital.repository.back.domain.vendor.dto.request.VendorDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.vendor.dto.response.VendorDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.vendor.dto.response.VendorDtoFindResponse;
import co.unicauca.digital.repository.back.domain.vendor.model.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Class that defines the mapper in charge of the O/R transition to a DTO object.
 */
@Mapper(componentModel = "spring")
public interface IVendorMapper {
    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "identification", source = "identification"),
            @Mapping(target = "createUser", source = "createUser"),
            @Mapping(target = "createTime", source = "createTime")
    })
    VendorDtoCreateResponse toDtoCreate(final Vendor vendor);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "identification", source = "identification")
    })
    VendorDtoFindResponse toDtoFind(final Vendor vendor);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "identification", source = "identification")
    })
    Vendor toEntityCreate(final VendorDtoCreateRequest vendorDtoCreateRequest);

}
