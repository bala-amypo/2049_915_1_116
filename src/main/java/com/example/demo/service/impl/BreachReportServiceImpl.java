package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final ContractRepository contractRepository;
    private final PenaltyCalculationRepository calculationRepository;
    private final BreachReportRepository reportRepository;

    public BreachReportServiceImpl(ContractRepository contractRepository,
                                   PenaltyCalculationRepository calculationRepository,
                                   BreachReportRepository reportRepository) {
        this.contractRepository = contractRepository;
        this.calculationRepository = calculationRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        PenaltyCalculation calculation = calculationRepository
                .findByContractId(contractId)
                .stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new RuntimeException("Penalty not calculated"));

        BreachReport report = new BreachReport();
        report.setContract(contract);
        report.setDaysDelayed(calculation.getDaysDelayed());
        report.setPenaltyAmount(calculation.getCalculatedPenalty());
        report.setRemarks("Contract breached due to delayed delivery");

        return reportRepository.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return reportRepository.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return reportRepository.findAll();
    }
}
