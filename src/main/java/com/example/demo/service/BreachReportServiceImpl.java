package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository reportRepo;
    private final PenaltyCalculationRepository penaltyRepo;
    private final ContractRepository contractRepo;

    public BreachReportServiceImpl(BreachReportRepository reportRepo,
                                   PenaltyCalculationRepository penaltyRepo,
                                   ContractRepository contractRepo) {
        this.reportRepo = reportRepo;
        this.penaltyRepo = penaltyRepo;
        this.contractRepo = contractRepo;
    }

    @Override
    public BreachReport generateReport(Long contractId) {
        contractRepo.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        PenaltyCalculation calc = penaltyRepo
                .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new RuntimeException("No calculation"));

        BreachReport report = new BreachReport();
        report.setContractId(contractId);
        report.setDaysDelayed(calc.getDaysDelayed());
        report.setPenaltyAmount(calc.getCalculatedPenalty());
        report.setReportStatus("GENERATED");
        report.setGeneratedAt(LocalDateTime.now());

        return reportRepo.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return reportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return reportRepo.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return reportRepo.findAll();
    }
}
