package com.example.demo.service.impl;

import com.example.demo.dto.PenaltyCalculationDto;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final ContractRepository contractRepository;
    private final PenaltyCalculationRepository repository;

    public PenaltyCalculationServiceImpl(ContractRepository contractRepository,
                                         PenaltyCalculationRepository repository) {
        this.contractRepository = contractRepository;
        this.repository = repository;
    }

    @Override
    public PenaltyCalculationDto calculatePenalty(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation calc = PenaltyCalculation.builder()
                .contract(contract)
                .daysDelayed(5)
                .calculatedPenalty(contract.getBaseContractValue())
                .calculatedAt(LocalDateTime.now())
                .build();

        repository.save(calc);

        PenaltyCalculationDto dto = new PenaltyCalculationDto();
        dto.setContractId(contractId);
        dto.setDaysDelayed(calc.getDaysDelayed());
        dto.setCalculatedPenalty(calc.getCalculatedPenalty());
        dto.setCalculatedAt(calc.getCalculatedAt());

        return dto;
    }
}
