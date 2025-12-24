package com.example.demo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {

    private Long contractId;
    private int delayDays;
    private double penaltyAmount;
}
