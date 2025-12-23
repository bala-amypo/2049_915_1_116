package com.example.demo.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {
    private Long id;
    private String ruleName;
    private double thresholdPercentage;
    private double penaltyRate;
    private boolean active;
}
