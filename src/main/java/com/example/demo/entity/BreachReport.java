package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;
    
    @Column(name = "report_generated_at")
    private LocalDateTime reportGeneratedAt;
    
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String remarks;
    
    @PrePersist
    protected void onCreate() {
        reportGeneratedAt = LocalDateTime.now();
    }
}