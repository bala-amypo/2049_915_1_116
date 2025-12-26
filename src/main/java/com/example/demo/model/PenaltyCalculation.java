package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_calculations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public PenaltyCalculation(Contract contract, DeliveryRecord deliveryRecord, BreachRule breachRule,
                             Integer daysDelayed, BigDecimal calculatedPenalty) {
        this.contract = contract;
        this.deliveryRecord = deliveryRecord;
        this.breachRule = breachRule;
        this.daysDelayed = daysDelayed;
        this.calculatedPenalty = calculatedPenalty;
    }

    @PrePersist
    protected void onCreate() {
        calculatedAt = LocalDateTime.now();
    }
}