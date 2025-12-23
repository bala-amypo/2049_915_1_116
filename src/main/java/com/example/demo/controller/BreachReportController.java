package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    private final BreachReportService breachReportService;

    public BreachReportController(BreachReportService breachReportService) {
        this.breachReportService = breachReportService;
    }

    @PostMapping("/generate/{contractId}")
    public BreachReport generate(@PathVariable Long contractId) {
        return breachReportService.generateReport(contractId);
    }

    @GetMapping
    public List<BreachReport> getAll() {
        return breachReportService.getAllReports();
    }

    @GetMapping("/contract/{contractId}")
    public List<BreachReport> getByContract(@PathVariable Long contractId) {
        return breachReportService.getReportsForContract(contractId);
    }
}
