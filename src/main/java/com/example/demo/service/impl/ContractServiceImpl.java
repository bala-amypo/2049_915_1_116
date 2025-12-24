package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;

import java.time.LocalDate;
import java.util.List;

public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    @Override
    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue() == null
                || contract.getBaseContractValue().doubleValue() <= 0) {
            throw new IllegalArgumentException("Base contract value must be greater than zero");
        }
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    @Override
    public Contract updateContract(Long id, Contract updated) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        existing.setTitle(updated.getTitle());
        existing.setCounterpartyName(updated.getCounterpartyName());
        existing.setAgreedDeliveryDate(updated.getAgreedDeliveryDate());
        existing.setBaseContractValue(updated.getBaseContractValue());

        return contractRepository.save(existing);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void updateContractStatus(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        var latestDelivery =
                deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(contractId);

        if (latestDelivery.isEmpty()) {
            contract.setStatus("ACTIVE");
        } else {
            LocalDate agreed = contract.getAgreedDeliveryDate();
            LocalDate delivered = latestDelivery.get().getDeliveryDate();
            if (delivered.isAfter(agreed)) {
                contract.setStatus("BREACHED");
            } else {
                contract.setStatus("ACTIVE");
            }
        }
        contractRepository.save(contract);
    }
}
