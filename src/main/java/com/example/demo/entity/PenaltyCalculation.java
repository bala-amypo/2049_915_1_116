package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private int daysDelayed;
    private BigDecimal calculatedPenalty;
    private LocalDateTime calculatedAt;

    public static PenaltyCalculationBuilder builder() {
        return new PenaltyCalculationBuilder();
    }

    public static class PenaltyCalculationBuilder {
        private PenaltyCalculation pc = new PenaltyCalculation();
        public PenaltyCalculationBuilder contract(Contract c){ pc.contract=c; return this;}
        public PenaltyCalculationBuilder daysDelayed(int d){ pc.daysDelayed=d; return this;}
        public PenaltyCalculationBuilder calculatedPenalty(BigDecimal p){ pc.calculatedPenalty=p; return this;}
        public PenaltyCalculationBuilder calculatedAt(LocalDateTime t){ pc.calculatedAt=t; return this;}
        public PenaltyCalculation build(){ return pc;}
    }

    public int getDaysDelayed() { return daysDelayed; }
    public BigDecimal getCalculatedPenalty() { return calculatedPenalty; }
}
