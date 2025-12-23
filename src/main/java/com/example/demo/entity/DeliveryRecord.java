package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private LocalDate deliveryDate;
    private int daysDelayed;

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public Contract getContract() { return contract; }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
