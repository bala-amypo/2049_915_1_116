package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRuleRepository breachRuleRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculationServiceImpl(
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository,
            BreachRuleRepository breachRuleRepository,
            PenaltyCalculationRepository penaltyCalculationRepository) {

        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRuleRepository = breachRuleRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        DeliveryRecord delivery = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new RuntimeException("Delivery record not found"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new RuntimeException("Breach rule not found"));

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                delivery.getDeliveryDate()
        );

        if (daysDelayed < 0) {
            daysDelayed = 0;
        }

        BigDecimal calculatedPenalty =
                rule.getPenaltyPerDay()
                        .multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxPenalty =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage()))
                        .divide(BigDecimal.valueOf(100));

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
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}
