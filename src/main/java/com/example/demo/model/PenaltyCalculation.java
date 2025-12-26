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

    @ManyToOne
    @JoinColumn(name = "delivery_record_id", nullable = false)
    private DeliveryRecord deliveryRecord;

    @ManyToOne
    @JoinColumn(name = "breach_rule_id", nullable = false)
    private BreachRule breachRule;

    @Column(nullable = false)
    private Integer delayDays;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyAmount;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    // 🔹 No-arg constructor (REQUIRED by JPA)
    public PenaltyCalculation() {
    }

    // 🔹 All-args constructor
    public PenaltyCalculation(DeliveryRecord deliveryRecord,
                              BreachRule breachRule,
                              Integer delayDays,
                              BigDecimal penaltyAmount) {
        this.deliveryRecord = deliveryRecord;
        this.breachRule = breachRule;
        this.delayDays = delayDays;
        this.penaltyAmount = penaltyAmount;
        this.calculatedAt = LocalDateTime.now();
    }

    // 🔹 Getters & Setters (THIS FIXES YOUR ERROR)

    public Long getId() {
        return id;
    }

    public DeliveryRecord getDeliveryRecord() {
        return deliveryRecord;
    }

    public void setDeliveryRecord(DeliveryRecord deliveryRecord) {
        this.deliveryRecord = deliveryRecord;
    }

    public BreachRule getBreachRule() {
        return breachRule;
    }

    public void setBreachRule(BreachRule breachRule) {
        this.breachRule = breachRule;
    }

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
