package com.example.demo.entity;

import java.math.BigDecimal;

public class BreachReport {
    private Long id;
    private Contract contract;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;

    // No-argument constructor
    public BreachReport() {}

    // All-argument constructor
    public BreachReport(Long id, Contract contract, Integer daysDelayed, BigDecimal penaltyAmount) {
        this.id = id;
        this.contract = contract;
        this.daysDelayed = daysDelayed;
        this.penaltyAmount = penaltyAmount;
    }

    // Builder pattern
    public static BreachReportBuilder builder() {
        return new BreachReportBuilder();
    }

    // Getters
    public Long getId() { return id; }
    public Contract getContract() { return contract; }
    public Integer getDaysDelayed() { return daysDelayed; }
    public BigDecimal getPenaltyAmount() { return penaltyAmount; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setContract(Contract contract) { this.contract = contract; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }
    public void setPenaltyAmount(BigDecimal penaltyAmount) { this.penaltyAmount = penaltyAmount; }

    // Builder class
    public static class BreachReportBuilder {
        private Long id;
        private Contract contract;
        private Integer daysDelayed;
        private BigDecimal penaltyAmount;

        public BreachReportBuilder id(Long id) { this.id = id; return this; }
        public BreachReportBuilder contract(Contract contract) { this.contract = contract; return this; }
        public BreachReportBuilder daysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; return this; }
        public BreachReportBuilder penaltyAmount(BigDecimal penaltyAmount) { this.penaltyAmount = penaltyAmount; return this; }

        public BreachReport build() {
            return new BreachReport(id, contract, daysDelayed, penaltyAmount);
        }
    }
}