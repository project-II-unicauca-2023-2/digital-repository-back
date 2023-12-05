package co.unicauca.digital.repository.back.domain.service.contract.impl;

import co.unicauca.digital.repository.back.domain.service.collection.ICollectionService;
import co.unicauca.digital.repository.back.domain.dto.aboutVendor.response.aboutVendorDto;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoAverageRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoCreateRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoIdRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.request.ContractDtoUpdateRequest;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoCreateResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoExpiredQualifiedResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindContractualFoldersResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindResponse;
import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractVendorDtoResponse;
import co.unicauca.digital.repository.back.domain.dto.vendor.response.VendorDtoAboutData;
import co.unicauca.digital.repository.back.domain.mapper.contract.IContractDtoExpiredQualifiedMapper;
import co.unicauca.digital.repository.back.domain.mapper.contract.IContractMapper;
import co.unicauca.digital.repository.back.domain.mapper.contract.IContractVendorMapper;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;
import co.unicauca.digital.repository.back.domain.model.contractType.ContractType;
import co.unicauca.digital.repository.back.domain.model.modality.Modality;
import co.unicauca.digital.repository.back.domain.repository.contract.IContractRepository;
import co.unicauca.digital.repository.back.domain.service.contract.IContractService;
import co.unicauca.digital.repository.back.domain.model.modalityContractType.ModalityContractType;
import co.unicauca.digital.repository.back.domain.model.score.Score;
import co.unicauca.digital.repository.back.domain.repository.modalityContractType.IModalityContractTypeRepository;
import co.unicauca.digital.repository.back.domain.repository.score.IScoreRepository;
import co.unicauca.digital.repository.back.domain.model.vendor.Vendor;
import co.unicauca.digital.repository.back.domain.repository.vendor.IVendorRepository;
import co.unicauca.digital.repository.back.global.exception.BusinessRuleException;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class in charge of implementing the IContractService interface
 * {@link IContractService}
 */
@Service
@Primary
public class ContractServiceImpl implements IContractService {

    /** Object to perform CRUD operations on the Product entity */
    private final IContractRepository contractRepository;
    /** Object to perform CRUD operations on the Product entity */
    private final IVendorRepository vendorRepository;
    /** Object to perform CRUD operations on the Score entity */
    private final IScoreRepository scoreRepository;
    /**
     * Object to perform CRUD operations on the ModalityContractTypeRepository
     * entity
     */
    private final IModalityContractTypeRepository modalityContractTypeRepository;

    /** Mapping object for mapping the products */
    private final IContractMapper contractMapper;

    private final IContractVendorMapper contractVendorMapper;

    private final ICollectionService collectionService;

    private final IContractDtoExpiredQualifiedMapper contractExpiredQualifiedMapper;

    /**
     * constructor method
     */
    public ContractServiceImpl(IContractRepository contractRepository, IVendorRepository vendorRepository,
            IModalityContractTypeRepository modalityContractTypeRepository, IContractMapper contractMapper,
            ICollectionService collectionService,IScoreRepository scoreRepository,IContractVendorMapper contractVendorMapper,
            IContractDtoExpiredQualifiedMapper contractExpiredQualifiedMapper) {
        this.contractRepository = contractRepository;
        this.vendorRepository = vendorRepository;
        this.modalityContractTypeRepository = modalityContractTypeRepository;
        this.contractMapper = contractMapper;
        this.collectionService = collectionService;
        this.scoreRepository = scoreRepository;
        this.contractVendorMapper = contractVendorMapper;
        this.contractExpiredQualifiedMapper = contractExpiredQualifiedMapper;
    }

    /**
     * @see IContractService#getById(int)
     */
    @Override
    public Response<ContractDtoFindResponse> getById(final int id) {
        Optional<Contract> contractFound = this.contractRepository.findById(id);
        if (contractFound.isEmpty())
            throw new BusinessRuleException("contract.request.not.found");
        ContractDtoFindResponse contractDtoFindResponse = contractMapper.toDtoFind(contractFound.get());

        // Set Vendor
        contractDtoFindResponse.setVendor(contractFound.get().getVendor().getIdentification());

        // Set ModalityContractType
        contractDtoFindResponse.setModalityContractType(contractFound.get().getModalityContractType().getId());

        return new ResponseHandler<>(200, "Encontrado", "Encontrado", contractDtoFindResponse).getResponse();
    }

    /**
     * @see IContractService#getAll(int,int)
     */
    @Override
    public Response<PageableResponse<Object>> getAll(final int pageNo, final int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Contract> responsePage = contractRepository.findAll(pageRequest);

        List<Object> contractDtoFindResponses = responsePage.get().map(
                contractMapper::toDtoFind).collect(Collectors.toList());

        PageableResponse<Object> response = PageableResponse.builder()
                .data(contractDtoFindResponses)
                .pageNo(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .build();

        return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();
    }

    /**
     * @see IContractService#createContract(ContractDtoCreateRequest)
     */
    @Override
    public Response<ContractDtoCreateResponse> createContract(final ContractDtoCreateRequest contractDtoCreateRequest) {
        String anio = Integer.toString(contractDtoCreateRequest.getInitialDate().getYear());
        ContractDtoIdRequest aux = new ContractDtoIdRequest(contractDtoCreateRequest.getReference(),anio);
        if (entityExistsByReference(aux).getData())
            throw new BusinessRuleException("contract.request.already.exists");

        Contract contractModel = contractMapper.toEntityCreate(contractDtoCreateRequest);
        contractModel.setCreateTime(LocalDateTime.now());
        contractModel.setSigningDate(contractModel.getInitialDate());

        // Set Vendor
        Optional<Vendor> vendor = vendorRepository.findByIdentification(contractDtoCreateRequest.getVendor());
        if (vendor.isEmpty())
            throw new BusinessRuleException("contract.vendor.association.error");
        contractModel.setVendor(vendor.get());

        // Set ModalityContractType
        Optional<ModalityContractType> modalityContractType = modalityContractTypeRepository.findByContractModality(
                contractDtoCreateRequest.getContractTypeId(), contractDtoCreateRequest.getModalityId());
        if (modalityContractType.isEmpty())
            throw new BusinessRuleException("contract.modalityContractType.association.error");
        contractModel.setModalityContractType(modalityContractType.get());

        // TODO Set create user
        Contract contractSaved = this.contractRepository.save(contractModel);

        // create collectinos's contract
        this.collectionService.createCollections(contractSaved);

        // Create a Score associated with the Contract
        Score score = Score.builder()
        .totalScore(0.0f)
        .createTime(LocalDateTime.now())
        .updateTime(LocalDateTime.now())
        .contract(contractSaved)
        .build();

        // Save the Score
        scoreRepository.save(score);

        ContractDtoCreateResponse contractDtoCreateResponse = contractMapper.toDtoCreate(contractSaved);

        return new ResponseHandler<>(200, "Contrato creado exitosamente", "Contrato creado exitosamente",
                contractDtoCreateResponse).getResponse();
    }

    /**
     * @see IContractService#updateContract(ContractDtoUpdateRequest)
     */
    @Override
    public Response<ContractDtoCreateResponse> updateContract(final ContractDtoUpdateRequest contractDtoUpdateRequest) {
        Optional<Contract> contract = contractRepository.findById(contractDtoUpdateRequest.getId());

        if (contract.isEmpty())
            throw new BusinessRuleException("contract.request.not.found");

        // Update DNI vendor
        Optional<Vendor> vendor = vendorRepository.findByIdentification(contractDtoUpdateRequest.getVendor());
        if (vendor.isEmpty())
            contract.get().getVendor().setIdentification(contractDtoUpdateRequest.getVendor());
        else
            contract.get().setVendor(vendor.get());

        Contract updateContract = Contract.builder()
                .id(contract.get().getId())
                .reference(contractDtoUpdateRequest.getReference())
                .signingDate(contract.get().getSigningDate())
                .initialDate(contractDtoUpdateRequest.getInitialDate())
                .finalDate(contractDtoUpdateRequest.getFinalDate())
                .status(contractDtoUpdateRequest.getStatus())
                .subject(contractDtoUpdateRequest.getSubject())
                .vendor(contract.get().getVendor())
                .modalityContractType(contract.get().getModalityContractType())
                .createUser(contract.get().getCreateUser())
                .createTime(contract.get().getCreateTime())
                .updateTime(LocalDateTime.now())
                // TODO .updateUser(XXX)
                .build();

        Contract contractSaved = this.contractRepository.save(updateContract);
        ContractDtoCreateResponse contractDtoCreateResponse = contractMapper.toDtoCreate(contractSaved);

        return new ResponseHandler<>(200, "Contrato actualizado exitosamente", "Contrato actualizado exitosamente",
                contractDtoCreateResponse).getResponse();
    }

    /**
     * @see IContractService#deleteContract(int)
     */
    @Override
    public Response<Boolean> deleteContract(final int id) {
        Optional<Contract> contract = contractRepository.findById(id);

        if (contract.isEmpty())
            throw new BusinessRuleException("contract.request.not.found");
        contractRepository.deleteById(id);

        return new ResponseHandler<>(200, "Contrato eliminado exitosamente", "Contrato eliminado exitosamente",
                !contractRepository.existsById(id)).getResponse();
    }

    /**
     * Check if the ContractDtoCreateRequest already exists
     *
     * @param reference the request to be validated
     * @return true if the entity exists
     */
    public Response<Boolean>entityExistsByReference(final ContractDtoIdRequest prmContractParams) {
        int numericYear = Integer.parseInt(prmContractParams.getAnio());
        boolean isContractRegister = contractRepository.findByReferenceAndYear(prmContractParams.getMascara(),numericYear).isPresent();
        String responseMessage = isContractRegister  ? "Ya existe un contrato registrado con esa mascara" : "No existe un contrato con esa mascara";
        return new ResponseHandler<>(200,responseMessage,responseMessage,
            isContractRegister).getResponse();
    }

    public Response<Boolean>ExistEvaluationByReference(final ContractDtoIdRequest prmContractParams) {
        int numericYear = Integer.parseInt(prmContractParams.getAnio());
        Optional<Contract> contract = contractRepository.findByReferenceAndYear(prmContractParams.getMascara(),numericYear);
        Contract objContrato = contract.orElseThrow(() -> new BusinessRuleException("contract.request.not.found"));
        Score objScore  = scoreRepository.findById(objContrato.getId()).get();
        boolean isEvaluationRegister = objScore.getCreateTime().equals(objScore.getUpdateTime()) ? false : true;
        String responseMessage = isEvaluationRegister ? "Ya existe una evaluacion registrada para el contrato asociado a la mascara solicitada" : "No existe una evaluacion registrada para el contrato asociado a la mascara solicitada";
        return new ResponseHandler<>(200,responseMessage,responseMessage,
            isEvaluationRegister).getResponse();
    }

    public Response<ContractVendorDtoResponse> DataContractVendorByMask(final ContractDtoIdRequest prmContractParams){
        int numericYear = Integer.parseInt(prmContractParams.getAnio());
        Optional<Contract> contract = contractRepository.findByReferenceAndYear(prmContractParams.getMascara(),numericYear);
        Contract objContrato = contract.orElseThrow(() -> new BusinessRuleException("contract.request.not.found"));
        Vendor vendor = objContrato.getVendor(); 
        ContractVendorDtoResponse objResponse = contractVendorMapper.toContractVendorDtoResponse(objContrato, vendor);
        objResponse.setNaturalPerson("NATURAL".equalsIgnoreCase(vendor.getPersonType()));
        return new ResponseHandler<>(200,"Datos del contrato y proveedor","Datos del contrato y proveedor",
           objResponse).getResponse();       
    }

    @Override
    public Response<PageableResponse<Object>> getContractualFoldersSortBySigningDate(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("signingDate").descending());
        Page<Contract> responsePage = contractRepository.findAll(pageRequest);

        List<Object> contractDtoFindContractualFolderResponse = responsePage.get()
                .map(contract -> {
                    ContractDtoFindContractualFoldersResponse response = this.contractMapper
                            .toDtoFindContractualFolders(contract);
                    response.setSigningYear(contract.getSigningDate().getYear());
                    response.setModality(contract.getModalityContractType().getModality().getName());
                    response.setContractType(contract.getModalityContractType().getContractType().getName());
                    response.setVendor(contract.getVendor().getIdentification());
                    return response;
                }).collect(Collectors.toList());

        PageableResponse<Object> response = PageableResponse.builder()
                .data(contractDtoFindContractualFolderResponse)
                .pageNo(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .build();

        return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();
    }

    @Override
    public Response<PageableResponse<Object>> getContractualFoldersByFilter(int pageNo, int pageSize, String filter,
            String search) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ContractDtoFindContractualFoldersResponse> responsePage = contractRepository
                .findByFilterAndSearchPattern(filter, search, pageRequest);

        List<Object> contractDtoFindContractualFolderResponse = responsePage.get()
                .collect(Collectors.toList());

        PageableResponse<Object> response = PageableResponse.builder()
                .data(contractDtoFindContractualFolderResponse)
                .pageNo(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .build();

        return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();
    }

    @Override
    public Response<List<ContractDtoAverageRequest>> getAverageContractByCategory(String description, int year) {
        

        ContractDtoAverageRequest averageContract = new ContractDtoAverageRequest();
        List<ContractDtoAverageRequest> ContractDtoList = new ArrayList<>();
        
        try{
        Float promedio =contractRepository.getAverageByCategory(description, year);
        averageContract.setAverageContract(promedio);

            if(promedio != null){
                averageContract.setAverageContract(promedio);
            ContractDtoList.add(averageContract);
            }
        }catch (Exception e) {
            // Capturar cualquier excepción inesperada y manejarla adecuadamente
            System.err.println("Error al procesar el promedio: " + e.getMessage());
        }    
    

        Response<List<ContractDtoAverageRequest>> response = new Response<>();
        if(ContractDtoList.size()>0){
        response.setStatus(200);
        response.setUserMessage("Average Finded successfully");
        response.setDeveloperMessage("Average Finded successfully");
        response.setData(ContractDtoList);
        }else{
            response.setStatus(500);
			response.setUserMessage("It was not possible to calculate the average");
			response.setDeveloperMessage("Data Not Found for contract");
        }
        return response;

               
        //return new ResponseHandler<>(200, "Encontrado", "Encontrado", averageContract).getResponse();
    }

    @Override
    public Response<List<ContractDtoExpiredQualifiedResponse>> getExpiredQualifiedContract(){
        Optional<List<Contract>> listaContratosExpirados = this.contractRepository.findByFinalDateBefore(LocalDateTime.now());
        List<Contract> varListaContratosExpirados = listaContratosExpirados.orElseThrow(() -> new BusinessRuleException("contract.request.not.found"));
        List<Contract> varListaContratosExpiradosCalificados = new ArrayList<>();
        List<ContractDtoExpiredQualifiedResponse> varListaContratosExpiradosCalificadosResponse = new ArrayList<>();
        varListaContratosExpirados.forEach( contratoExpirado -> {
            Score varScoreContratoExpirado = contratoExpirado.getScore();
            if(!varScoreContratoExpirado.getCreateTime().equals(varScoreContratoExpirado.getUpdateTime())){
                varListaContratosExpiradosCalificados.add(contratoExpirado);
            }
        });

        varListaContratosExpiradosCalificados.forEach( contratoExpiradoCalificado ->{
            Vendor objVendor = contratoExpiradoCalificado.getVendor();
            ModalityContractType objModalityContractType = contratoExpiradoCalificado.getModalityContractType();
            ContractType objContractType = objModalityContractType.getContractType();
            Modality objModality = objModalityContractType.getModality();
            Score objScore = contratoExpiradoCalificado.getScore();
            ContractDtoExpiredQualifiedResponse objContractDtoExpiredQualifiedResponse = this.contractExpiredQualifiedMapper.toContractExpiredQualifiedResponse(contratoExpiradoCalificado, objVendor, objContractType, objModality, objScore);
            objContractDtoExpiredQualifiedResponse.setInitialYear(contratoExpiradoCalificado.getInitialDate().getYear());
            varListaContratosExpiradosCalificadosResponse.add(objContractDtoExpiredQualifiedResponse);
        });
        return new ResponseHandler<>(200,"Información de contratos calificados vencidos.","Información de contratos calificados vencidos.",
        varListaContratosExpiradosCalificadosResponse).getResponse(); 
    }
}
    

