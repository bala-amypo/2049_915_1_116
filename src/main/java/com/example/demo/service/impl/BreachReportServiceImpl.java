package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;

    public BreachReportServiceImpl(BreachReportRepository breachReportRepository) {
        this.breachReportRepository = breachReportRepository;
    }

    @Override
    public BreachReport saveBreachReport(BreachReport report) {
        return breachReportRepository.save(report);
    }

    @Override
    public List<BreachReport> getAllBreachReports() {
        return breachReportRepository.findAll();
    }

    @Override
    public BreachReport getBreachReportById(Long id) {
        return breachReportRepository.findById(id).orElse(null);
    }
}
