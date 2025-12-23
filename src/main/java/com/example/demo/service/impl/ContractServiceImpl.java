package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    public ContractServiceImpl() { }

    // Setter injection for tests
    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public void setDeliveryRecordRepository(DeliveryRecordRepository deliveryRecordRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue() == null || contract.getBaseContractValue().doubleValue() <= 0) {
            throw new IllegalArgumentException("Base contract value must be greater than zero");
        }
        if (contract.getAgreedDeliveryDate() == null || contract.getAgreedDeliveryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Agreed delivery date cannot be in the past");
        }
        contract.setStatus("ACTIVE");
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
    public void updateContractStatus(Long contractId) {
        Contract contract = getContractById(contractId);
        Optional<DeliveryRecord> latest = deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(contractId);

        if (latest.isPresent()) {
            DeliveryRecord dr = latest.get();
            if (dr.getDeliveryDate().isAfter(contract.getAgreedDeliveryDate())) {
                contract.setStatus("BREACHED");
            } else {
                contract.setStatus("ACTIVE");
            }
        } else {
            contract.setStatus("ACTIVE");
        }

        contractRepository.save(contract);
    }
}

