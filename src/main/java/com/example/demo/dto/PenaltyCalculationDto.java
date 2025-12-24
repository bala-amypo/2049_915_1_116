package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculatorDto {
    private Long id;
    private String violationType;
    private BigDecimal amount;
    private boolean paid;
}
