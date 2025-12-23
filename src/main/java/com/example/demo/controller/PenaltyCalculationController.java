package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyCalculationController {

    private final PenaltyCalculationService penaltyCalculationService;

    public PenaltyCalculationController(PenaltyCalculationService penaltyCalculationService) {
        this.penaltyCalculationService = penaltyCalculationService;
    }

    @PostMapping("/calculate/{contractId}")
    public PenaltyCalculation calculate(@PathVariable Long contractId) {
        return penaltyCalculationService.calculatePenalty(contractId);
    }

    @GetMapping("/{id}")
    public PenaltyCalculation getById(@PathVariable Long id) {
        return penaltyCalculationService.getCalculationById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<PenaltyCalculation> getByContract(@PathVariable Long contractId) {
        return penaltyCalculationService.getCalculationsForContract(contractId);
    }
}
