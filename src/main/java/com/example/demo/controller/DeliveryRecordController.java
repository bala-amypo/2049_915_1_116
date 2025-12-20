package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    @GetMapping
    public String getAllDeliveryRecords() {
        return "Delivery records fetched successfully";
    }

    @PostMapping
    public String createDeliveryRecord() {
        return "Delivery record created successfully";
    }
}
