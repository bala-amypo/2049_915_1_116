package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository penaltyRepo;
    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleRepository ruleRepo;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository penaltyRepo,
                                         ContractRepository contractRepo,
                                         DeliveryRecordRepository deliveryRepo,
                                         BreachRuleRepository ruleRepo) {
        this.penaltyRepo = penaltyRepo;
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        DeliveryRecord latest = deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElse(null);

        BreachRule rule = ruleRepo.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new RuntimeException("No active rule"));

        int daysDelayed = latest == null ? 0 : 1;

        PenaltyCalculation calc = new PenaltyCalculation();
        calc.setContractId(contractId);
        calc.setDaysDelayed(daysDelayed);
        calc.setCalculatedPenalty(rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed)));
        calc.setCalculatedAt(LocalDateTime.now());

        return penaltyRepo.save(calc);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyRepo.findByContractId(contractId);
    }
}
