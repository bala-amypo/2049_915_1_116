package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.impl.DeliveryRecordServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordServiceImpl deliveryService;

    public DeliveryRecordController(DeliveryRecordServiceImpl deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public DeliveryRecord create(@RequestBody DeliveryRecord record) {
        return deliveryService.createDeliveryRecord(record);
    }

    @GetMapping("/{id}")
    public DeliveryRecord getById(@PathVariable Long id) {
        return deliveryService.getRecordById(id);
    }

    @GetMapping("/latest/{contractId}")
    public DeliveryRecord getLatest(@PathVariable Long contractId) {
        return deliveryService.getLatestDeliveryRecord(contractId);
    }

    @GetMapping("/contract/{contractId}")
    public List<DeliveryRecord> getByContract(@PathVariable Long contractId) {
        return deliveryService.getDeliveryRecordsForContract(contractId);
    }
}
