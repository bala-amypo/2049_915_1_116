
package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    public ContractServiceImpl() {
        this.contractRepository = null;
        this.deliveryRecordRepository = null;
    }

    public ContractServiceImpl(ContractRepository contractRepository, 
                              DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract createContract(Contract contract) {
        validateContract(contract);
        
        if (contractRepository.findByContractNumber(contract.getContractNumber()).isPresent()) {
            throw new BadRequestException("Contract with this number exists");
        }

        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existingContract = getContractById(id);
        
        validateContract(contract);
        
        Optional<Contract> duplicateContract = contractRepository.findByContractNumber(contract.getContractNumber());
        if (duplicateContract.isPresent() && !duplicateContract.get().getId().equals(id)) {
            throw new BadRequestException("Contract with this number exists");
        }

        existingContract.setContractNumber(contract.getContractNumber());
        existingContract.setTitle(contract.getTitle());
        existingContract.setCounterpartyName(contract.getCounterpartyName());
        existingContract.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
        existingContract.setBaseContractValue(contract.getBaseContractValue());
        if (contract.getStatus() != null) {
            existingContract.setStatus(contract.getStatus());
        }

        return contractRepository.save(existingContract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract contract = getContractById(id);
        Optional<DeliveryRecord> latestDelivery = deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id);
        
        if (latestDelivery.isPresent()) {
            LocalDate deliveryDate = latestDelivery.get().getDeliveryDate();
            if (deliveryDate.isAfter(contract.getAgreedDeliveryDate())) {
                contract.setStatus("BREACHED");
            } else {
                contract.setStatus("COMPLETED");
            }
        } else {
            // No delivery record, keep current status
        }
        
        contractRepository.save(contract);
    }

    private void validateContract(Contract contract) {
        if (contract.getBaseContractValue() == null || contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value must be greater than zero");
        }
        
        if (contract.getAgreedDeliveryDate() != null && contract.getAgreedDeliveryDate().isBefore(LocalDate.now())) {
            // Allow past dates for agreed delivery date as it's a contract term
        }
    }
}