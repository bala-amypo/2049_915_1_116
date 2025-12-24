package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PenaltyCalculation {
    private Long id;
    private Contract contract;
    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;
    private LocalDateTime calculatedAt;

    // No-argument constructor
    public PenaltyCalculation() {}

    // All-argument constructor
    public PenaltyCalculation(Long id, Contract contract, Integer daysDelayed, 
                             BigDecimal calculatedPenalty, LocalDateTime calculatedAt) {
        this.id = id;
        this.contract = contract;
        this.daysDelayed = daysDelayed;
        this.calculatedPenalty = calculatedPenalty;
        this.calculatedAt = calculatedAt;
    }

    // Builder pattern
    public static PenaltyCalculationBuilder builder() {
        return new PenaltyCalculationBuilder();
    }

    // Getters
    public Long getId() { return id; }
    public Contract getContract() { return contract; }
    public Integer getDaysDelayed() { return daysDelayed; }
    public BigDecimal getCalculatedPenalty() { return calculatedPenalty; }
    public LocalDateTime getCalculatedAt() { return calculatedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setContract(Contract contract) { this.contract = contract; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }
    public void setCalculatedPenalty(BigDecimal calculatedPenalty) { this.calculatedPenalty = calculatedPenalty; }
    public void setCalculatedAt(LocalDateTime calculatedAt) { this.calculatedAt = calculatedAt; }

    // Builder class
    public static class PenaltyCalculationBuilder {
        private Long id;
        private Contract contract;
        private Integer daysDelayed;
        private BigDecimal calculatedPenalty;
        private LocalDateTime calculatedAt;

        public PenaltyCalculationBuilder id(Long id) { this.id = id; return this; }
        public PenaltyCalculationBuilder contract(Contract contract) { this.contract = contract; return this; }
        public PenaltyCalculationBuilder daysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; return this; }
        public PenaltyCalculationBuilder calculatedPenalty(BigDecimal calculatedPenalty) { this.calculatedPenalty = calculatedPenalty; return this; }
        public PenaltyCalculationBuilder calculatedAt(LocalDateTime calculatedAt) { this.calculatedAt = calculatedAt; return this; }

        public PenaltyCalculation build() {
            return new PenaltyCalculation(id, contract, daysDelayed, calculatedPenalty, calculatedAt);
        }
    }
}