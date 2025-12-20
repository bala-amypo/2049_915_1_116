package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class BreachReportController {

    private final BreachReportService service;

    public BreachReportController(BreachReportService service) {
        this.service = service;
    }

    @PostMapping("/{contractId}")
    public ResponseEntity<BreachReport> generate(@PathVariable Long contractId) {
        return ResponseEntity.ok(service.generateReport(contractId));
    }
}
