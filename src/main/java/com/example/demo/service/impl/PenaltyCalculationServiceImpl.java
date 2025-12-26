
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

    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRuleRepository breachRuleRepository;

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
                .orElseThrow(() -> new ResourceNotFoundException("No delivery records found for contract"));

        BreachRule activeRule = breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule found"));

        // Calculate days delayed
        LocalDate agreedDate = contract.getAgreedDeliveryDate();
        LocalDate actualDate = latestDelivery.getDeliveryDate();
        
        int daysDelayed = 0;
        if (actualDate.isAfter(agreedDate)) {
            daysDelayed = (int) ChronoUnit.DAYS.between(agreedDate, actualDate);
        }

        // Calculate penalty
        BigDecimal penaltyAmount = BigDecimal.ZERO;
        if (daysDelayed > 0) {
            penaltyAmount = activeRule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed));
            
            // Apply maximum penalty cap
            BigDecimal maxPenalty = contract.getBaseContractValue()
                    .multiply(BigDecimal.valueOf(activeRule.getMaxPenaltyPercentage()))
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            
            if (penaltyAmount.compareTo(maxPenalty) > 0) {
                penaltyAmount = maxPenalty.setScale(1, RoundingMode.HALF_UP);
            }
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