package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository penaltyCalculationRepository) {
        this.penaltyCalculationRepository = penaltyCalculationRepository;
    }

    @Override
    public PenaltyCalculation savePenaltyCalculation(PenaltyCalculation penalty) {
        return penaltyCalculationRepository.save(penalty);
    }

    @Override
    public List<PenaltyCalculation> getAllPenaltyCalculations() {
        return penaltyCalculationRepository.findAll();
    }

    @Override
    public PenaltyCalculation getPenaltyCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id).orElse(null);
    }
}
