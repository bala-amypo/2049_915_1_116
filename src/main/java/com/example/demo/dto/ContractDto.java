package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ContractDto {

    private String contractName;
    private BigDecimal penaltyPerDay;
    private LocalDate startDate;
    private LocalDate endDate;
}
