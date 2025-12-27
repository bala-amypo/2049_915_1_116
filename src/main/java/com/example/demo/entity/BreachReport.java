package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private long daysDelayed;

    private double penaltyAmount;

    @ManyToOne
    private PenaltyCalculation penaltyCalculation;

    @ManyToOne
    private Contract contract;   // 🔥 REQUIRED
}
