package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_calculations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "delivery_record_id")
    private DeliveryRecord deliveryRecord;

    @ManyToOne(optional = false)
    @JoinColumn(name = "breach_rule_id")
    private BreachRule breachRule;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false)
    private BigDecimal calculatedPenalty;

    @Column(updatable = false)
    private LocalDateTime calculatedAt;

    @PrePersist
    public void prePersist() {
        this.calculatedAt = LocalDateTime.now();
    }
}
