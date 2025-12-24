package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleName;

    private BigDecimal penaltyPerDay;

    private Double maxPenaltyPercentage;

    private Boolean active;

    private Boolean isDefaultRule;
}
