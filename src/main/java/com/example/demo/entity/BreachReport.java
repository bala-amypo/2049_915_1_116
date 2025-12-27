package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportName;

    private LocalDateTime reportDate;

    @ManyToOne
    @JoinColumn(name = "breach_rule_id")
    private BreachRule breachRule;

    private String status;
}
