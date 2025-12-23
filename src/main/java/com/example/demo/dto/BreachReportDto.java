package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BreachResponseDto {

    private Long contractId;
    private String message;
    private Double penaltyAmount;
}
