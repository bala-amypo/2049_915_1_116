package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractServiceImpl {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract updateContract(long id, Contract contract) {
        Optional<Contract> existing = contractRepository.findById(id);
        if (existing.isPresent()) {
            Contract c = existing.get();
            c.setContractNumber(contract.getContractNumber());
            c.setBaseContractValue(contract.getBaseContractValue());
            c.setStatus(contract.getStatus());
            return contractRepository.save(c);
        }
        return null;
    }

    public void updateContractStatus(long id) {
        Optional<Contract> existing = contractRepository.findById(id);
        existing.ifPresent(c -> {
            c.setStatus("UPDATED");
            contractRepository.save(c);
        });
    }
}
