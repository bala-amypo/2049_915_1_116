package com.example.demo.controller;

import com.example.demo.dto.DeliveryRecordDto;
import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.ContractService;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryService;
    private final ContractService contractService;

    public DeliveryRecordController(DeliveryRecordService deliveryService,
                                    ContractService contractService) {
        this.deliveryService = deliveryService;
        this.contractService = contractService;
    }

    @PostMapping
    public DeliveryRecord add(@RequestBody DeliveryRecordDto dto) {

        Contract contract = contractService.getContractById(dto.getContractId());

        DeliveryRecord record = DeliveryRecord.builder()
                .contract(contract)
                .deliveryDate(dto.getDeliveryDate())
                .deliveredOnTime(dto.isDeliveredOnTime())
                .build();

        return deliveryService.addDeliveryRecord(record);
    }
}
