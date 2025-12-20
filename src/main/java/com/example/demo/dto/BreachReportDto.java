package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BreachReportDto {

    private Long id;
    private Long contractId;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String reportStatus;
    private LocalDateTime generatedAt;
}
