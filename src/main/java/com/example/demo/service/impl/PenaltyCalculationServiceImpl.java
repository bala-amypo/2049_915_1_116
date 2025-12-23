package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl {

    private final PenaltyCalculationRepository penaltyRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleRepository breachRuleRepo;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository penaltyRepo,
                                         DeliveryRecordRepository deliveryRepo,
                                         BreachRuleRepository breachRuleRepo) {
        this.penaltyRepo = penaltyRepo;
        this.deliveryRepo = deliveryRepo;
        this.breachRuleRepo = breachRuleRepo;
    }

    public PenaltyCalculation calculatePenalty(Long contractId) {

        DeliveryRecord latest = deliveryRepo
                .findTopByContractIdOrderByDeliveryDateDesc(contractId);

        List<BreachRule> rules = breachRuleRepo.findByActiveTrue();

        double penalty = 0.0;

        if (latest != null) {
            int delivered = latest.getDeliveredQuantity();
            int expected = latest.getExpectedQuantity();
            int breachPercent = 100 - (delivered * 100 / expected);

            for (BreachRule rule : rules) {
                if (breachPercent >= rule.getThresholdPercentage()) {
                    penalty += rule.getPenaltyRate();
                }
            }
        }

        PenaltyCalculation calc = PenaltyCalculation.builder()
                .contractId(contractId)
                .penaltyAmount(penalty)
                .calculatedAt(LocalDateTime.now())
                .build();

        return penaltyRepo.save(calc);
    }

    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Penalty not found"));
    }

    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyRepo.findByContractId(contractId);
    }
}
