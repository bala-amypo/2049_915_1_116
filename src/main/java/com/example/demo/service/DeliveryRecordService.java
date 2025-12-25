package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;

import java.util.List;

public interface DeliveryRecordService {

    DeliveryRecord addDeliveryRecord(Long contractId, DeliveryRecord record);

    List<DeliveryRecord> getDeliveryRecordsByContract(Long contractId);
}
