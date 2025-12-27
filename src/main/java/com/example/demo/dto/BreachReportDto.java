package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BreachReportDto {
    private Long contractId;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String remarks;
}