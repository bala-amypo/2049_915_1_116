package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreachReportDto {

    private Long id;
    private Long contractId;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String reportStatus;
    private LocalDateTime generatedAt;
}
