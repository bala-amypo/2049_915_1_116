package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-reports")
public class BreachReportController {

    private final BreachReportService breachReportService;

    public BreachReportController(BreachReportService breachReportService) {
        this.breachReportService = breachReportService;
    }

    @PostMapping("/contract/{contractId}")
    public ResponseEntity<BreachReport> generateReport(@PathVariable Long contractId) {
        BreachReport report = breachReportService.generateReport(contractId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreachReport> getBreachReport(@PathVariable Long id) {
        BreachReport report = breachReportService.getReportById(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<BreachReport>> getReportsForContract(@PathVariable Long contractId) {
        List<BreachReport> reports = breachReportService.getReportsForContract(contractId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping
    public ResponseEntity<List<BreachReport>> getAllReports() {
        List<BreachReport> reports = breachReportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}