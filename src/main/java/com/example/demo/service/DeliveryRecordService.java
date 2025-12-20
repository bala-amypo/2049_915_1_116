package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordService {

    DeliveryRecord saveDeliveryRecord(DeliveryRecord record);

    List<DeliveryRecord> getAllDeliveryRecords();

    DeliveryRecord getDeliveryRecordById(Long id);
}
