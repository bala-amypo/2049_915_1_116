package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ContractDto {

    private Long id;
    private String contractNumber;
    private String title;
    private String counterpartyName;
