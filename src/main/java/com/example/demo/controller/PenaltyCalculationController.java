package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalty-calculations")
public class PenaltyCalculationController {

    private final PenaltyCalculationService penaltyCalculationService;

    public PenaltyCalculationController(PenaltyCalculationService penaltyCalculationService) {
        this.penaltyCalculationService = penaltyCalculationService;
    }

    @PostMapping
    public PenaltyCalculation createPenaltyCalculation(
            @RequestBody PenaltyCalculation penaltyCalculation) {
        return penaltyCalculationService.savePenaltyCalculation(penaltyCalculation);
    }

    @GetMapping
    public List<PenaltyCalculation> getAllPenaltyCalculations() {
        return penaltyCalculationService.getAllPenaltyCalculations();
    }

    @GetMapping("/{id}")
    public PenaltyCalculation getPenaltyCalculationById(@PathVariable Long id) {
        return penaltyCalculationService.getPenaltyCalculationById(id);
    }
}
