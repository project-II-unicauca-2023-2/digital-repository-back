package co.unicauca.digital.repository.back.domain.contract.service.impl;

import co.unicauca.digital.repository.back.domain.contract.dto.response.ContractDtoFindContractualFoldersResponse;
import co.unicauca.digital.repository.back.domain.contract.mapper.IContractMapper;
import co.unicauca.digital.repository.back.domain.contract.mapper.IContractDtoFindContractualFoldersMapper;
import co.unicauca.digital.repository.back.domain.contract.model.Contract;
import co.unicauca.digital.repository.back.domain.contract.model.ContractDtoFindContractualFolders;
import co.unicauca.digital.repository.back.domain.contract.repository.IContractFilterAndSearchPatternRepository;
import co.unicauca.digital.repository.back.domain.contract.repository.IContractRepository;
import co.unicauca.digital.repository.back.domain.contract.service.IListContractualFolders;
import co.unicauca.digital.repository.back.global.response.PageableResponse;
import co.unicauca.digital.repository.back.global.response.Response;
import co.unicauca.digital.repository.back.global.response.handler.ResponseHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListContractualFolders implements IListContractualFolders {

    /** Object to perform CRUD operations on the Contract entity */
    private final IContractRepository contractRepository;

    /** Object to perform CRUD operations on the Contract filter and search pattern */
    private final IContractFilterAndSearchPatternRepository findByFilterAndSearchPattern;

    /** Mapping object for mapping the Contract */
    private final IContractMapper contractMapper;

    /** Mapping object for mapping the Contract when trying to filter by patterns */
    private final IContractDtoFindContractualFoldersMapper iContractDtoFindContractualFoldersMapper;

    /** Constructor */
    public ListContractualFolders(IContractRepository contractRepository, IContractFilterAndSearchPatternRepository findByFilterAndSearchPattern, IContractMapper contractMapper, IContractDtoFindContractualFoldersMapper iContractDtoFindContractualFoldersMapper) {
        this.contractRepository = contractRepository;
        this.findByFilterAndSearchPattern = findByFilterAndSearchPattern;
        this.contractMapper = contractMapper;
        this.iContractDtoFindContractualFoldersMapper = iContractDtoFindContractualFoldersMapper;
    }

    /**
     * @see IListContractualFolders#getContractualFoldersSortBySigningDate(int,int)
     */
    @Override
    public Response<PageableResponse<Object>> getContractualFoldersSortBySigningDate(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("signingDate").descending());
        Page<Contract> responsePage = contractRepository.findAll(pageRequest);

        List<Object> contractDtoFindContractualFolderResponse = responsePage.get()
                .map(contract -> {
                    ContractDtoFindContractualFoldersResponse response = this.contractMapper.toDtoFindContractualFolders(contract);
                    response.setSigningYear(contract.getSigningDate().getYear());
                    response.setModality(contract.getModalityContractType().getModality().getName());
                    response.setContractType(contract.getModalityContractType().getContractType().getName());
                    response.setVendor(contract.getVendor().getIdentification());
                    return response;
                }
        ).collect(Collectors.toList());

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
    public Response<PageableResponse<Object>> getContractualFoldersByFilter(int pageNo, int pageSize, String filter, String search) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<ContractDtoFindContractualFolders> responsePage = findByFilterAndSearchPattern.findByFilterAndSearchPattern(filter, search, pageRequest);

        List<Object> contractDtoFindContractualFolderResponse = responsePage.get()
                .map(
                        this.iContractDtoFindContractualFoldersMapper::toDtoFind
                ).collect(Collectors.toList());

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
}
