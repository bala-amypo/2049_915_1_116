package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreachRuleDto {

    private String ruleName;
    private Integer thresholdPercentage;
    private Double penaltyRate;
}
