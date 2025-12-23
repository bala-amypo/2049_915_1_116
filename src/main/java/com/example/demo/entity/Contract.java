package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String counterpartyName;
    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCounterpartyName() { return counterpartyName; }
    public LocalDate getAgreedDeliveryDate() { return agreedDeliveryDate; }
    public BigDecimal getBaseContractValue() { return baseContractValue; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
