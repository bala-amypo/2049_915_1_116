package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_calculations")
public class PenaltyCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_record_id")
    private DeliveryRecord deliveryRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breach_rule_id", nullable = false)
    private BreachRule breachRule;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal calculatedPenalty;

    @Column(nullable = false, updatable = false)
    private LocalDateTime calculatedAt;

    public PenaltyCalculation() {}

    public PenaltyCalculation(Contract contract, DeliveryRecord deliveryRecord, BreachRule breachRule,
                             Integer daysDelayed, BigDecimal calculatedPenalty) {
        this.contract = contract;
        this.deliveryRecord = deliveryRecord;
        this.breachRule = breachRule;
        this.daysDelayed = daysDelayed;
        this.calculatedPenalty = calculatedPenalty;
    }

    // Builder pattern
    public static PenaltyCalculationBuilder builder() {
        return new PenaltyCalculationBuilder();
    }

    public static class PenaltyCalculationBuilder {
        private Long id;
        private Contract contract;
        private DeliveryRecord deliveryRecord;
        private BreachRule breachRule;
        private Integer daysDelayed;
        private BigDecimal calculatedPenalty;

        public PenaltyCalculationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PenaltyCalculationBuilder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public PenaltyCalculationBuilder deliveryRecord(DeliveryRecord deliveryRecord) {
            this.deliveryRecord = deliveryRecord;
            return this;
        }

        public PenaltyCalculationBuilder breachRule(BreachRule breachRule) {
            this.breachRule = breachRule;
            return this;
        }

        public PenaltyCalculationBuilder daysDelayed(Integer daysDelayed) {
            this.daysDelayed = daysDelayed;
            return this;
        }

        public PenaltyCalculationBuilder calculatedPenalty(BigDecimal calculatedPenalty) {
            this.calculatedPenalty = calculatedPenalty;
            return this;
        }

        public PenaltyCalculation build() {
            PenaltyCalculation calculation = new PenaltyCalculation();
            calculation.id = this.id;
            calculation.contract = this.contract;
            calculation.deliveryRecord = this.deliveryRecord;
            calculation.breachRule = this.breachRule;
            calculation.daysDelayed = this.daysDelayed;
            calculation.calculatedPenalty = this.calculatedPenalty;
            return calculation;
        }
    }

    @PrePersist
    protected void onCreate() {
        calculatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    public DeliveryRecord getDeliveryRecord() { return deliveryRecord; }
    public void setDeliveryRecord(DeliveryRecord deliveryRecord) { this.deliveryRecord = deliveryRecord; }

    public BreachRule getBreachRule() { return breachRule; }
    public void setBreachRule(BreachRule breachRule) { this.breachRule = breachRule; }

    public Integer getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(Integer daysDelayed) { this.daysDelayed = daysDelayed; }

    public BigDecimal getCalculatedPenalty() { return calculatedPenalty; }
    public void setCalculatedPenalty(BigDecimal calculatedPenalty) { this.calculatedPenalty = calculatedPenalty; }

    public LocalDateTime getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(LocalDateTime calculatedAt) { this.calculatedAt = calculatedAt; }
}