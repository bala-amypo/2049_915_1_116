package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

import java.time.LocalDate;
import java.util.List;

public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private DeliveryRecordRepository deliveryRecordRepository;
    private ContractRepository contractRepository;

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {
        if (record.getDeliveryDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Delivery date cannot be in the future");
        }

        Long contractId = record.getContract().getId();
        contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        return deliveryRecordRepository.save(record);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new RuntimeException("No delivery records found"));
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return deliveryRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery record not found"));
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return deliveryRecordRepository.findByContractIdOrderByDeliveryDateAsc(contractId);
    }
}
