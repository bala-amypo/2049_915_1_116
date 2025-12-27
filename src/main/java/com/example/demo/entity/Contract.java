package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String counterpartyName;

    private LocalDate agreedDeliveryDate;

    private BigDecimal baseContractValue;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    public enum ContractStatus {
        ACTIVE,
        COMPLETED,
        BREACHED,
        DELAYED,
        CANCELLED
    }
}
