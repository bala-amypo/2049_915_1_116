package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository penaltyCalculationRepository;
    private final ContractRepository contractRepository;

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        PenaltyCalculation calculation = new PenaltyCalculation();
        calculation.setContractId(contractId);
        calculation.setDaysDelayed(0);
        calculation.setCalculatedPenalty(BigDecimal.ZERO);
        calculation.setCalculatedAt(LocalDateTime.now());

        return penaltyCalculationRepository.save(calculation);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Penalty calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}
