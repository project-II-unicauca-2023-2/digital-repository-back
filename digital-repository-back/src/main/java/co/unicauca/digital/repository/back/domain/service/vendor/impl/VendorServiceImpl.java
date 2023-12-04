package co.unicauca.digital.repository.back.domain.service.vendor.impl;

import co.unicauca.digital.repository.back.domain.dto.vendor.request.VendorDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.vendor.request.VendorDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.dto.vendor.response.VendorDtoAboutData;
import co.unicauca.digital.repository.back.domain.dto.vendor.response.VendorDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.vendor.response.VendorDtoFindResponse;
import co.unicauca.digital.repository.back.domain.mapper.vendor.IVendorMapper;
import co.unicauca.digital.repository.back.domain.model.vendor.Vendor;
import co.unicauca.digital.repository.back.domain.repository.vendor.IVendorRepository;
import co.unicauca.digital.repository.back.domain.service.vendor.IVendorService;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.Null;

/**
 * Class in charge of implementing the IVendorService interface
 * {@link IVendorService}
 */
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
        if (vendorFound.isEmpty())
            throw new BusinessRuleException("vendor.request.not.found");
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
                vendorMapper::toDtoFind).collect(Collectors.toList());

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
        if (entityExistsByIdentification(vendorDtoCreateRequest.getIdentification()))
            throw new BusinessRuleException("vendor.request.already.exists");

        Vendor vendorModel = vendorMapper.toEntityCreate(vendorDtoCreateRequest);
        vendorModel.setCreateTime(LocalDateTime.now());
        // TODO Set create user
        Vendor vendorSaved = this.vendorRepository.save(vendorModel);
        VendorDtoCreateResponse vendorDtoCreateResponse = vendorMapper.toDtoCreate(vendorSaved);

        return new ResponseHandler<>(200, "Contratista creado exitosamente", "Contratista creado exitosamente",
                vendorDtoCreateResponse).getResponse();
    }

    /**
     * @see IVendorService#updateVendor(VendorDtoUpdateRequest)
     */
    @Override
    public Response<VendorDtoCreateResponse> updateVendor(VendorDtoUpdateRequest vendorDtoUpdateRequest) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorDtoUpdateRequest.getId());

        if (vendor.isEmpty())
            throw new BusinessRuleException("vendor.request.not.found");

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

        return new ResponseHandler<>(200, "Contratista actualizado exitosamente",
                "Contratista actualizado exitosamente", vendorDtoCreateResponse).getResponse();
    }

    /**
     * @see IVendorService#deleteVendor(int)
     */
    @Override
    public Response<Boolean> deleteVendor(int id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);

        if (vendor.isEmpty())
            throw new BusinessRuleException("vendor.request.not.found");
        vendorRepository.deleteById(id);

        return new ResponseHandler<>(200, "Contratista eliminado exitosamente", "Contratista eliminado exitosamente",
                !vendorRepository.existsById(id)).getResponse();
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

    /**
     * Search and retrieve seller data with the requested parameters
     *
     * @param year 
     * @param  idsVendors
     * @return List from vendors
     */
    @Override
    public Response<List<VendorDtoAboutData>> getDataAboutVendors(int year, List<Integer> idsVendors) {

        
        List<VendorDtoAboutData> VendorsDTOList = new ArrayList<>();
        int cantidadLista = idsVendors.size();
        
        for(int i =0; i<cantidadLista; i++){
            List<String> result=vendorRepository.findVendorData(year, idsVendors.get(i));

            if(!result.isEmpty()){
                String str = result.get(0);
                String[] valuesVendor = str.split(",");

                VendorDtoAboutData vendorData =  new VendorDtoAboutData();

                vendorData.setIdVendor(Integer.parseInt(valuesVendor[0]));
                vendorData.setNameVendor(valuesVendor[1]);
                vendorData.setCredential(valuesVendor[2]);
                vendorData.setNumContract(Integer.parseInt(valuesVendor[3]));
                vendorData.setScoreYear(Float.parseFloat(valuesVendor[4]));
                vendorData.setNumContractYear(Integer.parseInt(valuesVendor[5]));
                vendorData.setScoreGeneral(Float.parseFloat(valuesVendor[6]));

            VendorsDTOList.add(vendorData);
            }else{
                System.out.println("No data found for vendor with ID: " + idsVendors.get(i));
            }

            
        }
        

        Response<List<VendorDtoAboutData>> response = new Response<>();
        if(VendorsDTOList.size()>0){
        response.setStatus(200);
        response.setUserMessage("List of vendors Finded successfully");
        response.setDeveloperMessage("List of vendors Finded successfully");
        response.setData(VendorsDTOList);
        }else{
            response.setStatus(500);
			response.setUserMessage("Data Not Found");
			response.setDeveloperMessage("Data Not Found");
        }
        return response;
    }

}
