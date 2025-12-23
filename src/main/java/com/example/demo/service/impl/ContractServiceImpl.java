package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract createContract(Contract contract) {

        // ✅ FIX: correct getters
        contract.setName(contract.getName());
        contract.setValue(contract.getValue());

        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {

        Contract existing = getContractById(id);

        // ✅ FIX
        existing.setName(contract.getName());
        existing.setValue(contract.getValue());

        return contractRepository.save(existing);
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract contract = getContractById(id);
        contract.setActive(false);
        contractRepository.save(contract);
    }
}
