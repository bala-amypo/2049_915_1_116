package com.example.demo.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {
    private Long id;
    private Long contractId;
    private Long breachRuleId;
    private double penaltyAmount;
}
