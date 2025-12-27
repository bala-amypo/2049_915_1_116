package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_records")
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private VendorTier vendor;

    @Column(nullable = false)
    private LocalDateTime deliveryTime;

    @Column(nullable = false)
    private boolean delayed;

    @PrePersist
    public void prePersist() {
        if (deliveryTime == null) {
            deliveryTime = LocalDateTime.now();
        }
    }

    // getters & setters
}
