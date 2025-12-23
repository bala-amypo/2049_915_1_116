package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.impl.PenaltyCalculationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyCalculationController {

    private final PenaltyCalculationServiceImpl penaltyService;

    public PenaltyCalculationController(PenaltyCalculationServiceImpl penaltyService) {
        this.penaltyService = penaltyService;
    }

    @PostMapping("/{contractId}")
    public PenaltyCalculation calculate(@PathVariable Long contractId) {
        return penaltyService.calculatePenalty(contractId);
    }

    @GetMapping("/{id}")
    public PenaltyCalculation getById(@PathVariable Long id) {
        return penaltyService.getCalculationById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<PenaltyCalculation> getByContract(@PathVariable Long contractId) {
        return penaltyService.getCalculationsForContract(contractId);
    }
}
