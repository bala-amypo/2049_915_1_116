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

    // Many penalty calculations can belong to one contract
    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false)
    private BigDecimal calculatedPenalty;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    // =====================
    // Constructors
    // =====================

    public PenaltyCalculation() {
        this.calculatedAt = LocalDateTime.now();
    }

    public PenaltyCalculation(Long id, Contract contract, Integer daysDelayed,
                              BigDecimal calculatedPenalty, LocalDateTime calculatedAt) {
        this.id = id;
        this.contract = contract;
        this.daysDelayed = daysDelayed;
        this.calculatedPenalty = calculatedPenalty;
        this.calculatedAt = calculatedAt != null ? calculatedAt : LocalDateTime.now();
    }

    // =====================
    // Builder (for tests)
    // =====================
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Contract contract;
        private Integer daysDelayed;
        private BigDecimal calculatedPenalty;
        private LocalDateTime calculatedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public Builder daysDelayed(Integer daysDelayed) {
            this.daysDelayed = daysDelayed;
            return this;
        }

        public Builder calculatedPenalty(BigDecimal calculatedPenalty) {
            this.calculatedPenalty = calculatedPenalty;
            return this;
        }

        public Builder calculatedAt(LocalDateTime calculatedAt) {
            this.calculatedAt = calculatedAt;
            return this;
        }

        public PenaltyCalculation build() {
            return new PenaltyCalculation(
                    id,
                    contract,
                    daysDelayed != null ? daysDelayed : 0,
                    calculatedPenalty != null ? calculatedPenalty : BigDecimal.ZERO,
                    calculatedAt
            );
        }
    }

    // =====================
    // Getters and Setters
    // =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(Integer daysDelayed) {
        if (daysDelayed < 0) {
            throw new IllegalArgumentException("Days delayed cannot be negative");
        }
        this.daysDelayed = daysDelayed;
    }

    public BigDecimal getCalculatedPenalty() {
        return calculatedPenalty;
    }

    public void setCalculatedPenalty(BigDecimal calculatedPenalty) {
        if (calculatedPenalty.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Calculated penalty cannot be negative");
        }
        this.calculatedPenalty = calculatedPenalty;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
