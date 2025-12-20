package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordService service;

    public DeliveryRecordController(DeliveryRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeliveryRecord> create(@RequestBody DeliveryRecord r) {
        return ResponseEntity.ok(service.createDeliveryRecord(r));
    }

    @GetMapping("/contract/{id}")
    public ResponseEntity<List<DeliveryRecord>> byContract(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDeliveryRecordsForContract(id));
    }
}
