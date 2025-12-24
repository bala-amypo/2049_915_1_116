package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PenaltyCalculationDto {

    private Long contractId;
    private BigDecimal totalPenalty;
}
