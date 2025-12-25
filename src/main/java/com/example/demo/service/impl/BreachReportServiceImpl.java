package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final ContractRepository contractRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final BreachReportRepository breachReportRepository;

    public BreachReportServiceImpl(ContractRepository contractRepository,
                                   PenaltyCalculationRepository penaltyCalculationRepository,
                                   BreachReportRepository breachReportRepository) {
        this.contractRepository = contractRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.breachReportRepository = breachReportRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation calculation = penaltyCalculationRepository
                .findByContract(
                        contractRepository.findById(contractId).get()
                )
                .orElseThrow(() -> new ResourceNotFoundException("No penalty calculation"));

        BreachReport report = new BreachReport();
        report.setContract(calculation.getContract());
        report.setDaysDelayed(calculation.getDaysDelayed());
        report.setPenaltyAmount(calculation.getCalculatedPenalty());
        report.setRemarks("Penalty calculated successfully");
        report.setReportGeneratedAt(LocalDateTime.now());

        return breachReportRepository.save(report);
    }
}
