package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
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
    public DeliveryRecord createDeliveryRecord(@RequestBody DeliveryRecord record) {
        return deliveryRecordService.saveDeliveryRecord(record);
    }

    @GetMapping
    public List<DeliveryRecord> getAllDeliveryRecords() {
        return deliveryRecordService.getAllDeliveryRecords();
    }

    @GetMapping("/{id}")
    public DeliveryRecord getDeliveryRecordById(@PathVariable Long id) {
        return deliveryRecordService.getDeliveryRecordById(id);
    }
}
