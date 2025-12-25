package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRecordRepository;
    private final ContractRepository contractRepository;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRecordRepository,
                                     ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public DeliveryRecord addDeliveryRecord(Long contractId, DeliveryRecord record) {

        if (record.getDeliveryDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Delivery date cannot be in the future");
        }

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        record.setContract(contract);
        record.setCreatedAt(LocalDateTime.now());

        return deliveryRecordRepository.save(record);
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsByContract(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        List<DeliveryRecord> records = deliveryRecordRepository.findByContract(contract);

        if (records.isEmpty()) {
            throw new ResourceNotFoundException("No delivery records found");
        }

        return records;
    }
}
