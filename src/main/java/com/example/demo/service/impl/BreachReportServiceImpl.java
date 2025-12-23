package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private BreachReportRepository breachReportRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;

    public BreachReportServiceImpl() { }

    // Setter injection for tests
    public void setBreachReportRepository(BreachReportRepository breachReportRepository) {
        this.breachReportRepository = breachReportRepository;
    }

    public void setPenaltyCalculationRepository(PenaltyCalculationRepository penaltyCalculationRepository) {
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public BreachReport generateReport(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        PenaltyCalculation calculation = penaltyCalculationRepository.findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new RuntimeException("No penalty calculation found for contract"));

        BreachReport report = BreachReport.builder()
                .contract(contract)
                .daysDelayed(calculation.getDaysDelayed())
                .penaltyAmount(calculation.getCalculatedPenalty())
                .build();

        return breachReportRepository.save(report);
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
