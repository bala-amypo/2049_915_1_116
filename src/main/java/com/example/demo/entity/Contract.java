package com.example.demo.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private Long id;
    private String contractName;
    private double contractValue;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
}
