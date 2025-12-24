package com.example.demo.dto;

import java.time.LocalDate;

public class DeliveryRecordDto {
    private Long contractId;
    private LocalDate deliveryDate;
    private String notes;

    // No-argument constructor
    public DeliveryRecordDto() {}

    // All-argument constructor
    public DeliveryRecordDto(Long contractId, LocalDate deliveryDate, String notes) {
        this.contractId = contractId;
        this.deliveryDate = deliveryDate;
        this.notes = notes;
    }

    // Getters
    public Long getContractId() { return contractId; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public String getNotes() { return notes; }

    // Setters
    public void setContractId(Long contractId) { this.contractId = contractId; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    public void setNotes(String notes) { this.notes = notes; }
}