package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class PenaltyCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Contract contract;
    
    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;
    private LocalDateTime calculatedAt;
    
    public PenaltyCalculation() {}
    
    public static PenaltyCalculationBuilder builder() { return new PenaltyCalculationBuilder(); }
    
    public static class PenaltyCalculationBuilder {
        private PenaltyCalculation calc = new PenaltyCalculation();
        
        public PenaltyCalculationBuilder id(Long id) { calc.id = id; return this; }
        public PenaltyCalculationBuilder contract(Contract contract) { calc.contract = contract; return this; }
        public PenaltyCalculationBuilder daysDelayed(Integer daysDelayed) { calc.daysDelayed = daysDelayed; return this; }
        public PenaltyCalculationBuilder calculatedPenalty(BigDecimal calculatedPenalty) { calc.calculatedPenalty = calculatedPenalty; return this; }
        public PenaltyCalculationBuilder calculatedAt(LocalDateTime calculatedAt) { calc.calculatedAt = calculatedAt; return this; }
        
        public PenaltyCalculation build() { return calc; }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }
    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }
    public BigDecimal getCalculatedPenalty() { return calculatedPenalty; }
    public void setCalculatedPenalty(BigDecimal calculatedPenalty) { this.calculatedPenalty = calculatedPenalty; }
    public LocalDateTime getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(LocalDateTime calculatedAt) { this.calculatedAt = calculatedAt; }
}