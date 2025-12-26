package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyAmount;

    @Column(nullable = false)
    private String reportStatus = "GENERATED";

    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt;

    public BreachReport(Contract contract, Integer daysDelayed, BigDecimal penaltyAmount) {
        this.contract = contract;
        this.daysDelayed = daysDelayed;
        this.penaltyAmount = penaltyAmount;
    }

    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }
}