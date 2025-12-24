package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository repository;

    public BreachReportServiceImpl(BreachReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachReport generateReport(BreachReport report) {
        return repository.save(report);
    }
}
