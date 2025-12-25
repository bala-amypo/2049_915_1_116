package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    private final BreachReportService breachReportService;

    public BreachReportController(BreachReportService breachReportService) {
        this.breachReportService = breachReportService;
    }

    @PostMapping("/{contractId}")
    public BreachReport generateReport(@PathVariable Long contractId) {
        return breachReportService.generateReport(contractId);
    }
}
