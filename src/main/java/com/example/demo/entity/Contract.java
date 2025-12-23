package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber; // corresponds to getContractNumber()

    @Column(nullable = false)
    private String title; // corresponds to getTitle()

    @Column(name = "counterparty_name", nullable = false)
    private String counterpartyName; // corresponds to getCounterpartyName()

    @Column(name = "agreed_delivery_date", nullable = false)
    private LocalDate agreedDeliveryDate; // corresponds to getAgreedDeliveryDate()

    @Column(name = "base_contract_value", nullable = false)
    private BigDecimal baseContractValue; // corresponds to getBaseContractValue()

    @Column(nullable = false)
    private String status; // ACTIVE / BREACHED etc.

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
