package com.example.demo.controller;

import com.example.demo.dto.ContractDto;
import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public Contract create(@RequestBody ContractDto dto) {

        Contract contract = Contract.builder()
                .contractName(dto.getContractName())
                .penaltyPerDay(dto.getPenaltyPerDay())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        return contractService.createContract(contract);
    }

    @GetMapping
    public List<Contract> getAll() {
        return contractService.getAllContracts();
    }
}
