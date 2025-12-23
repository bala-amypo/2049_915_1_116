package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BreachReportServiceImpl {

    private final BreachReportRepository breachReportRepository;

    public BreachReportServiceImpl(BreachReportRepository breachReportRepository) {
        this.breachReportRepository = breachReportRepository;
    }

    public BreachReport generateReport(Long contractId) {

        BreachReport report = BreachReport.builder()
                .contractId(contractId)
                .breachSummary("Breach detected for contract " + contractId)
                .generatedAt(LocalDateTime.now())
                .build();

        return breachReportRepository.save(report);
    }

    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }

    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}
