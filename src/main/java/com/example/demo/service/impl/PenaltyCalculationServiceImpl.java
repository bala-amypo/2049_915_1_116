package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;
    private BreachRuleRepository breachRuleRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        DeliveryRecord deliveryRecord =
                deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                        .orElseThrow(() -> new RuntimeException("No delivery record"));

        BreachRule rule =
                breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                        .orElseThrow(() -> new RuntimeException("No active breach rule"));

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                deliveryRecord.getDeliveryDate()
        );

        if (daysDelayed < 0) {
            daysDelayed = 0;
        }

        // penalty = daysDelayed * penaltyPerDay
        BigDecimal calculatedPenalty =
                rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed));

        // maxPenalty = baseContractValue * (maxPenaltyPercentage / 100)
        BigDecimal maxPenalty =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage()))
                        .divide(BigDecimal.valueOf(100));

        // apply cap
        if (calculatedPenalty.compareTo(maxPenalty) > 0) {
            calculatedPenalty = maxPenalty;
        }

        PenaltyCalculation calculation = PenaltyCalculation.builder()
                .contract(contract)
                .daysDelayed((int) daysDelayed)
                .calculatedPenalty(calculatedPenalty)
                .build();

        return penaltyCalculationRepository.save(calculation);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }
}
