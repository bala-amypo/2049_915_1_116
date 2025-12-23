package com.example.demo.entity;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {
    private Long id;
    private String reportName;
    private String description;
    private LocalDate reportDate;
    private boolean active;
}
