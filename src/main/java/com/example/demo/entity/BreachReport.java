package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryRecord deliveryRecord;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime reportedAt;

    @PrePersist
    public void prePersist() {
        reportedAt = LocalDateTime.now();
    }

    // getters & setters
}
