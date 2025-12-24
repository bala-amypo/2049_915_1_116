package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreachReportDto {
    private Long id;
    private String reportName;
    private String description;
    private LocalDate reportDate;
    private boolean resolved;
}
