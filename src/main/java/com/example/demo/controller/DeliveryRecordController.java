package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    @Autowired
    private DeliveryRecordService deliveryRecordService;

    @PostMapping
    public ResponseEntity<DeliveryRecord> createDeliveryRecord(@RequestBody DeliveryRecord record) {
        return ResponseEntity.ok(deliveryRecordService.createDeliveryRecord(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRecord> getDeliveryRecord(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryRecordService.getRecordById(id));
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<DeliveryRecord>> getDeliveryRecordsByContract(@PathVariable Long contractId) {
        return ResponseEntity.ok(deliveryRecordService.getDeliveryRecordsForContract(contractId));
    }

    @GetMapping("/contract/{contractId}/latest")
    public ResponseEntity<DeliveryRecord> getLatestDeliveryRecord(@PathVariable Long contractId) {
        return ResponseEntity.ok(deliveryRecordService.getLatestDeliveryRecord(contractId));
    }
}