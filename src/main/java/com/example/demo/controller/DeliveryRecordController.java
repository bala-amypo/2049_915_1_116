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

    @PostMapping("/{contractId}")
    public DeliveryRecord addDeliveryRecord(@PathVariable Long contractId,
                                            @RequestBody DeliveryRecord record) {
        return deliveryRecordService.addDeliveryRecord(contractId, record);
    }

    @GetMapping("/{contractId}")
    public List<DeliveryRecord> getDeliveryRecords(@PathVariable Long contractId) {
        return deliveryRecordService.getDeliveryRecordsByContract(contractId);
    }
}
