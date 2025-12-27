package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {
    
    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;
    private BreachRuleRepository breachRuleRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    
    public PenaltyCalculationServiceImpl() {}
    
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
        
        DeliveryRecord latestDelivery = deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
            .orElseThrow(() -> new ResourceNotFoundException("No delivery record found"));
        
        BreachRule rule = breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
            .orElseThrow(() -> new ResourceNotFoundException("No active breach rule found"));
        
        long daysDelayed = ChronoUnit.DAYS.between(contract.getAgreedDeliveryDate(), latestDelivery.getDeliveryDate());
        if (daysDelayed < 0) daysDelayed = 0;
        
        BigDecimal penalty = rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed));
        BigDecimal maxPenalty = contract.getBaseContractValue()
            .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage()))
            .divide(BigDecimal.valueOf(100));
        
        if (penalty.compareTo(maxPenalty) > 0) {
            penalty = maxPenalty;
        }
        
        PenaltyCalculation calculation = PenaltyCalculation.builder()
            .contract(contract)
            .daysDelayed((int) daysDelayed)
            .calculatedPenalty(penalty)
            .appliedRule(rule)
            .build();
        
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