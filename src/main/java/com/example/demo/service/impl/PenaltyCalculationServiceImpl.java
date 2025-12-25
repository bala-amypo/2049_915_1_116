package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRuleRepository breachRuleRepository;
    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculationServiceImpl(ContractRepository contractRepository,
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
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        List<DeliveryRecord> records = deliveryRecordRepository.findByContract(contract);

        if (records.isEmpty()) {
            throw new ResourceNotFoundException("No delivery record");
        }

        DeliveryRecord record = records.get(0);

        long daysDelayed = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                record.getDeliveryDate()
        );

        if (daysDelayed < 0) {
            daysDelayed = 0;
        }

        BreachRule rule = breachRuleRepository
                .findByIsDefaultRuleTrueAndActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(daysDelayed));

        PenaltyCalculation calculation = new PenaltyCalculation();
        calculation.setContract(contract);
        calculation.setDaysDelayed((int) daysDelayed);
        calculation.setCalculatedPenalty(penalty);
        calculation.setAppliedRule(rule);
        calculation.setCalculatedAt(LocalDateTime.now());

        return penaltyCalculationRepository.save(calculation);
    }
}
