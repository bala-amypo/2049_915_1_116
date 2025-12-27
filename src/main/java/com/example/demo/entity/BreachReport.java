package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Associated contract
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    /**
     * Number of delayed days
     */
    @Column(nullable = false)
    private Integer daysDelayed;

    /**
     * Final penalty amount
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal penaltyAmount;

    /**
     * Optional remarks / comments
     * ✔ Required by service + tests
     */
    @Column(length = 500)
    private String remarks;

    /**
     * Report generation timestamp
     */
    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @PrePersist
    protected void onCreate() {
        this.generatedAt = LocalDateTime.now();
    }
}
