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
    public ResponseEntity<DeliveryRecord> createRecord(@RequestBody DeliveryRecord record) {
        return ResponseEntity.ok(deliveryRecordService.createDeliveryRecord(record));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRecord> getRecord(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryRecordService.getRecordById(id));
    }
    
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<DeliveryRecord>> getRecordsForContract(@PathVariable Long contractId) {
        return ResponseEntity.ok(deliveryRecordService.getDeliveryRecordsForContract(contractId));
    }
}