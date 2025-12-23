package com.example.demo.service;

import com.example.demo.dto.PenaltyCalculationDto;

public interface PenaltyCalculationService {

    PenaltyCalculationDto calculatePenalty(Long contractId);
}
