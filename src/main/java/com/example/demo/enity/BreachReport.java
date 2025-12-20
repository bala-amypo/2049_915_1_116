package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Contract contract;

    @Column(nullable = false)
    private Integer daysDelayed;

    @Column(nullable = false)
    private BigDecimal penaltyAmount;

    private String reportStatus = "GENERATED";

    private LocalDateTime generatedAt;

    @PrePersist
    public void onGenerate() {
        this.generatedAt = LocalDateTime.now();
        if (this.reportStatus == null) {
            this.reportStatus = "GENERATED";
        }
    }
}
