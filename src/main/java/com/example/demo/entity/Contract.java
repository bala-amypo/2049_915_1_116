package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String contractNumber;

    private String title;

    private String counterpartyName;

    private LocalDate agreedDeliveryDate;

    private BigDecimal baseContractValue;

    private String status; // ACTIVE, COMPLETED, BREACHED

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
