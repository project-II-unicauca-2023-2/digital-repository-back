// package co.unicauca.digital.repository.back.domain.service.contract.impl;

// import co.unicauca.digital.repository.back.domain.dto.contract.response.ContractDtoFindContractualFoldersResponse;
// import co.unicauca.digital.repository.back.domain.mapper.contract.IContractMapper;
// import co.unicauca.digital.repository.back.domain.model.contract.Contract;
// import co.unicauca.digital.repository.back.domain.repository.contract.IContractRepository;
// import co.unicauca.digital.repository.back.domain.service.contract.IListContractualFolders;
// import co.unicauca.digital.repository.back.global.response.PageableResponse;
// import co.unicauca.digital.repository.back.global.response.Response;
// import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class ListContractualFolders implements IListContractualFolders {

//     /** Object to perform CRUD operations on the Contract entity */
//     private final IContractRepository contractRepository;

//     /** Mapping object for mapping the Contract */
//     private final IContractMapper contractMapper;

//     /** Constructor */
//     public ListContractualFolders(IContractRepository contractRepository, IContractMapper contractMapper) {
//         this.contractRepository = contractRepository;
//         this.contractMapper = contractMapper;
//     }

//     /**
//      * @see IListContractualFolders#getContractualFoldersSortBySigningDate(int,int)
//      */
//     @Override
//     public Response<PageableResponse<Object>> getContractualFoldersSortBySigningDate(int pageNo, int pageSize) {
//         PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("signingDate").descending());
//         Page<Contract> responsePage = contractRepository.findAll(pageRequest);

//         List<Object> contractDtoFindContractualFolderResponse = responsePage.get()
//                 .map(contract -> {
//                     ContractDtoFindContractualFoldersResponse response = this.contractMapper
//                             .toDtoFindContractualFolders(contract);
//                     response.setSigningYear(contract.getSigningDate().getYear());
//                     response.setModality(contract.getModalityContractType().getModality().getName());
//                     response.setContractType(contract.getModalityContractType().getContractType().getName());
//                     response.setVendor(contract.getVendor().getIdentification());
//                     return response;
//                 }).collect(Collectors.toList());

//         PageableResponse<Object> response = PageableResponse.builder()
//                 .data(contractDtoFindContractualFolderResponse)
//                 .pageNo(responsePage.getNumber())
//                 .pageSize(responsePage.getSize())
//                 .totalElements(responsePage.getTotalElements())
//                 .totalPages(responsePage.getTotalPages())
//                 .last(responsePage.isLast())
//                 .build();

//         return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();
//     }

//     @Override
//     public Response<PageableResponse<Object>> getContractualFoldersByFilter(int pageNo, int pageSize, String filter,
//             String search) {
//         PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
//         Page<ContractDtoFindContractualFoldersResponse> responsePage = contractRepository
//                 .findByFilterAndSearchPattern(filter, search, pageRequest);

//         List<Object> contractDtoFindContractualFolderResponse = responsePage.get()
//                 .collect(Collectors.toList());

//         PageableResponse<Object> response = PageableResponse.builder()
//                 .data(contractDtoFindContractualFolderResponse)
//                 .pageNo(responsePage.getNumber())
//                 .pageSize(responsePage.getSize())
//                 .totalElements(responsePage.getTotalElements())
//                 .totalPages(responsePage.getTotalPages())
//                 .last(responsePage.isLast())
//                 .build();

//         return new ResponseHandler<>(200, "Exitoso", "Exitoso", response).getResponse();
//     }
// }
