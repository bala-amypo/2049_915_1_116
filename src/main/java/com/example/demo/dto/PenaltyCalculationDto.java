package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenaltyCalculationDto {

    private Long contractId;
    private Double penaltyAmount;
}
