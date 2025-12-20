package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public Contract createContract(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    @PutMapping("/{id}")
    public Contract updateContract(@PathVariable Long id,
                                   @RequestBody Contract contract) {
        return contractService.updateContract(id, contract);
    }

    @GetMapping("/{id}")
    public Contract getContract(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }

    @PutMapping("/{id}/status")
    public Contract updateStatus(@PathVariable Long id) {
        return contractService.updateContractStatus(id);
    }
}
