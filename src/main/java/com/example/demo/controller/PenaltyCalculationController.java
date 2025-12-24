package com.example.demo.controller;

import com.example.demo.dto.PenaltyCalculationDto;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.ContractService;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/penalties")
public class PenaltyCalculationController {

    private final PenaltyCalculationService service;
    private final ContractService contractService;

    public PenaltyCalculationController(PenaltyCalculationService service,
                                        ContractService contractService) {
        this.service = service;
        this.contractService = contractService;
    }

    @PostMapping
    public PenaltyCalculation calculate(@RequestBody PenaltyCalculationDto dto) {

        Contract contract = contractService.getContractById(dto.getContractId());

        PenaltyCalculation penalty = PenaltyCalculation.builder()
                .contract(contract)
                .totalPenalty(dto.getTotalPenalty())
                .calculatedDate(LocalDate.now())
                .build();

        return service.savePenalty(penalty);
    }
}
