package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
    name = "breach_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private BigDecimal penaltyPerDay;

    @Column(nullable = false)
    private Double maxPenaltyPercentage;

    @Column(nullable = false)
    private Boolean active;

    private Boolean isDefaultRule;

    @PrePersist
    public void prePersist() {
        if (this.active == null) {
            this.active = true;
        }
        if (this.isDefaultRule == null) {
            this.isDefaultRule = false;
        }
    }
}
