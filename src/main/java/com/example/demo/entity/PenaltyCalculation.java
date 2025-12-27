package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "breach_report_id")
    private BreachReport breachReport;

    private BigDecimal calculatedPenalty;

    private String calculationStatus;
}
