package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private Double baseContractValue;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    public enum ContractStatus {
        ACTIVE,
        COMPLETED,
        DELAYED,
        CANCELLED
    }
}
