package com.example.demo.service;

import com.example.demo.entity.PenaltyCalculation;
import java.util.List;

public interface PenaltyCalculationService {

    PenaltyCalculation savePenaltyCalculation(PenaltyCalculation penalty);

    List<PenaltyCalculation> getAllPenaltyCalculations();

    PenaltyCalculation getPenaltyCalculationById(Long id);
}
