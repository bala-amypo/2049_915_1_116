package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository breachReportRepository;

    @Override
    public BreachReport generateReport(Long contractId) {
        BreachReport report = new BreachReport();
        report.setContractId(contractId);
        report.setReportStatus("GENERATED");
        report.setGeneratedAt(LocalDateTime.now());
        return breachReportRepository.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return breachReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breach report not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}
