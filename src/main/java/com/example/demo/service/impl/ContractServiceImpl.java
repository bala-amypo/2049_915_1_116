package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue() == null || contract.getBaseContractValue().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value must be greater than 0");
        }
        contract.setStatus("ACTIVE");
        return contractRepository.save(contract);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract updateContract(Long id, Contract updated) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        if (updated.getTitle() != null) existing.setTitle(updated.getTitle());
        if (updated.getCounterpartyName() != null) existing.setCounterpartyName(updated.getCounterpartyName());
        if (updated.getAgreedDeliveryDate() != null) existing.setAgreedDeliveryDate(updated.getAgreedDeliveryDate());
        if (updated.getBaseContractValue() != null) existing.setBaseContractValue(updated.getBaseContractValue());
        return contractRepository.save(existing);
    }

    public void updateContractStatus(Long contractId) {
        Contract c = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        Optional<DeliveryRecord> latest = deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(contractId);
        if (latest.isEmpty()) {
            c.setStatus("ACTIVE");
        } else {
            DeliveryRecord dr = latest.get();
            if (dr.getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
                c.setStatus("BREACHED");
            } else {
                c.setStatus("ACTIVE");
            }
        }
        contractRepository.save(c);
    }

    // Setter injection for tests
    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public void setDeliveryRecordRepository(DeliveryRecordRepository deliveryRecordRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
    }
}
