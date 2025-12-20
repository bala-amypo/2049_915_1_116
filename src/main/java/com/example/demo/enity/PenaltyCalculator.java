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

    @ManyToOne(optional = false)
    private Contract contract;

    @ManyToOne
    private DeliveryRecord deliveryRecord;

    @ManyToOne(optional = false)
    private BreachRule breachRule;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false)
    private BigDecimal calculatedPenalty;

    @Column(nullable = false, updatable = false)
    private LocalDateTime calculatedAt;

    public PenaltyCalculation() {}

    public PenaltyCalculation(Contract contract, BreachRule breachRule,
                              Integer daysDelayed, BigDecimal calculatedPenalty) {
        this.contract = contract;
        this.breachRule = breachRule;
        this.daysDelayed = daysDelayed;
        this.calculatedPenalty = calculatedPenalty;
    }

    @PrePersist
    public void onCreate() {
        this.calculatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Contract getContract() { return contract; }
    public DeliveryRecord getDeliveryRecord() { return deliveryRecord; }
    public BreachRule getBreachRule() { return breachRule; }
    public Integer getDaysDelayed() { return daysDelayed; }
    public BigDecimal getCalculatedPenalty() { return calculatedPenalty; }
    public LocalDateTime getCalculatedAt() { return calculatedAt; }
}
