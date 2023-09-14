package co.unicauca.digital.repository.back.domain.vendor.service.impl;

import co.unicauca.digital.repository.back.domain.vendor.dto.request.VendorDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.vendor.dto.request.VendorDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.vendor.dto.response.VendorDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.vendor.dto.response.VendorDtoFindResponse;
import co.unicauca.digital.repository.back.domain.vendor.mapper.IVendorMapper;
import co.unicauca.digital.repository.back.domain.vendor.model.Vendor;
import co.unicauca.digital.repository.back.domain.vendor.repository.IVendorRepository;
import co.unicauca.digital.repository.back.domain.vendor.service.IVendorService;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Class in charge of implementing the IVendorService interface {@link IVendorService} */
@Service
@Primary
public class VendorServiceImpl implements IVendorService {

    /** Object to perform CRUD operations on the Vendor entity */
    private final IVendorRepository vendorRepository;

    /** Mapping object for mapping the vendors */
    private final IVendorMapper vendorMapper;

    /**
     * constructor method
     */
    public VendorServiceImpl(IVendorRepository vendorRepository, IVendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    /**
     * @see IVendorService#getById(int)
     */
    @Override
    public Response<VendorDtoFindResponse> getById(int id) {
        Optional<Vendor> vendorFound = this.vendorRepository.findById(id);
        if (vendorFound.isEmpty()) throw new BusinessRuleException("vendor.request.not.found");
        VendorDtoFindResponse vendorDtoFindResponse = vendorMapper.toDtoFind(vendorFound.get());


        return new ResponseHandler<>(200, "Encontrado", "Encontrado", vendorDtoFindResponse).getResponse();
    }

    /**
     * @see IVendorService#getAll(int,int)
     */
    @Override
    public Response<PageableResponse<Object>> getAll(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Vendor> responsePage = vendorRepository.findAll(pageRequest);

        List<Object> vendorDtoFindResponses = responsePage.get().map(
                vendorMapper::toDtoFind
        ).collect(Collectors.toList());


        PageableResponse<Object> response = PageableResponse.builder()
                .data(vendorDtoFindResponses)
                .pageNo(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .build();

        return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();
    }

    /**
     * @see IVendorService#createVendor(VendorDtoCreateRequest)
     */
    @Override
    public Response<VendorDtoCreateResponse> createVendor(final VendorDtoCreateRequest vendorDtoCreateRequest) {
        if(entityExistsByIdentification(vendorDtoCreateRequest.getIdentification())) throw new BusinessRuleException("vendor.request.already.exists");

        Vendor vendorModel = vendorMapper.toEntityCreate(vendorDtoCreateRequest);
        vendorModel.setCreateTime(LocalDateTime.now());
        // TODO Set create user
        Vendor vendorSaved = this.vendorRepository.save(vendorModel);
        VendorDtoCreateResponse vendorDtoCreateResponse = vendorMapper.toDtoCreate(vendorSaved);

        return new ResponseHandler<>(200, "Contratista creado exitosamente", "Contratista creado exitosamente", vendorDtoCreateResponse).getResponse();
    }

    /**
     * @see IVendorService#updateVendor(VendorDtoUpdateRequest)
     */
    @Override
    public Response<VendorDtoCreateResponse> updateVendor(VendorDtoUpdateRequest vendorDtoUpdateRequest) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorDtoUpdateRequest.getId());

        if (vendor.isEmpty()) throw new BusinessRuleException("vendor.request.not.found");

        Vendor updateVendor = Vendor.builder()
                .id(vendor.get().getId())
                .name(vendorDtoUpdateRequest.getName())
                .identification(vendorDtoUpdateRequest.getIdentification())
                .createUser(vendor.get().getCreateUser())
                .createTime(vendor.get().getCreateTime())
                .updateTime(LocalDateTime.now())
                // TODO .updateUser(XXX)
                .build();

        Vendor vendorSaved = this.vendorRepository.save(updateVendor);
        VendorDtoCreateResponse vendorDtoCreateResponse = vendorMapper.toDtoCreate(vendorSaved);

        return new ResponseHandler<>(200, "Contratista actualizado exitosamente", "Contratista actualizado exitosamente", vendorDtoCreateResponse).getResponse();
    }

    /**
     * @see IVendorService#deleteVendor(int)
     */
    @Override
    public Response<Boolean> deleteVendor(int id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);

        if (vendor.isEmpty()) throw new BusinessRuleException("vendor.request.not.found");
        vendorRepository.deleteById(id);

        return new ResponseHandler<>(200, "Contratista eliminado exitosamente", "Contratista eliminado exitosamente", !vendorRepository.existsById(id)).getResponse();
    }

    /**
     * Check if the VendorDtoCreateRequest already exists
     *
     * @param identification the request to be validated
     * @return true if the entity exists
     */
    private boolean entityExistsByIdentification(final String identification) {
        return vendorRepository.findByIdentification(identification).isPresent();
    }



}
