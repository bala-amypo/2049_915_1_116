package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;
    private BreachRuleRepository breachRuleRepository;

    public PenaltyCalculationServiceImpl() {
        this.penaltyCalculationRepository = null;
        this.contractRepository = null;
        this.deliveryRecordRepository = null;
        this.breachRuleRepository = null;
    }

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository penaltyCalculationRepository,
                                       ContractRepository contractRepository,
                                       DeliveryRecordRepository deliveryRecordRepository,
                                       BreachRuleRepository breachRuleRepository) {
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord latestDelivery = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record found"));

        BreachRule activeRule = breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule found"));

        LocalDate agreedDate = contract.getAgreedDeliveryDate();
        LocalDate actualDate = latestDelivery.getDeliveryDate();
        
        int daysDelayed = 0;
        if (actualDate.isAfter(agreedDate)) {
            daysDelayed = (int) ChronoUnit.DAYS.between(agreedDate, actualDate);
        }

        BigDecimal penaltyAmount = BigDecimal.ZERO;
        if (daysDelayed > 0) {
            BigDecimal totalPenalty = activeRule.getPenaltyPerDay()
                    .multiply(BigDecimal.valueOf(daysDelayed));
            
            BigDecimal maxPenalty = contract.getBaseContractValue()
                    .multiply(BigDecimal.valueOf(activeRule.getMaxPenaltyPercentage()))
                    .divide(BigDecimal.valueOf(100), 1, RoundingMode.HALF_UP);
            
            penaltyAmount = totalPenalty.min(maxPenalty);
        }

        PenaltyCalculation calculation = new PenaltyCalculation();
        calculation.setContract(contract);
        calculation.setDeliveryRecord(latestDelivery);
        calculation.setBreachRule(activeRule);
        calculation.setDaysDelayed(daysDelayed);
        calculation.setCalculatedPenalty(penaltyAmount);

        return penaltyCalculationRepository.save(calculation);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}