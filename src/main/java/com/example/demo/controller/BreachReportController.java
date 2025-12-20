package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-reports")
public class BreachReportController {

    private final BreachReportService breachReportService;

    public BreachReportController(BreachReportService breachReportService) {
        this.breachReportService = breachReportService;
    }

    @PostMapping
    public BreachReport createBreachReport(@RequestBody BreachReport report) {
        return breachReportService.saveBreachReport(report);
    }

    @GetMapping
    public List<BreachReport> getAllBreachReports() {
        return breachReportService.getAllBreachReports();
    }

    @GetMapping("/{id}")
    public BreachReport getBreachReportById(@PathVariable Long id) {
        return breachReportService.getBreachReportById(id);
    }
}
