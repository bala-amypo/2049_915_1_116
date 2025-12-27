package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryRecordService;

    public DeliveryRecordController(DeliveryRecordService deliveryRecordService) {
        this.deliveryRecordService = deliveryRecordService;
    }

    @PostMapping
    public ResponseEntity<DeliveryRecord> createDeliveryRecord(@RequestBody DeliveryRecord record) {
        DeliveryRecord created = deliveryRecordService.createDeliveryRecord(record);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRecord> getDeliveryRecord(@PathVariable Long id) {
        DeliveryRecord record = deliveryRecordService.getRecordById(id);
        return ResponseEntity.ok(record);
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<DeliveryRecord>> getDeliveryRecordsForContract(@PathVariable Long contractId) {
        List<DeliveryRecord> records = deliveryRecordService.getDeliveryRecordsForContract(contractId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/contract/{contractId}/latest")
    public ResponseEntity<DeliveryRecord> getLatestDeliveryRecord(@PathVariable Long contractId) {
        DeliveryRecord record = deliveryRecordService.getLatestDeliveryRecord(contractId);
        return ResponseEntity.ok(record);
    }
}