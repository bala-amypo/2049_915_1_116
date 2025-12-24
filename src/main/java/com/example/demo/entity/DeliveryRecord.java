package com.example.demo.entity;

import java.time.LocalDate;

public class DeliveryRecord {
    private Long id;
    private Contract contract;
    private LocalDate deliveryDate;
    private String notes;

    // No-argument constructor
    public DeliveryRecord() {}

    // All-argument constructor
    public DeliveryRecord(Long id, Contract contract, LocalDate deliveryDate, String notes) {
        this.id = id;
        this.contract = contract;
        this.deliveryDate = deliveryDate;
        this.notes = notes;
    }

    // Builder pattern
    public static DeliveryRecordBuilder builder() {
        return new DeliveryRecordBuilder();
    }

    // Getters
    public Long getId() { return id; }
    public Contract getContract() { return contract; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public String getNotes() { return notes; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setContract(Contract contract) { this.contract = contract; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    public void setNotes(String notes) { this.notes = notes; }

    // Builder class
    public static class DeliveryRecordBuilder {
        private Long id;
        private Contract contract;
        private LocalDate deliveryDate;
        private String notes;

        public DeliveryRecordBuilder id(Long id) { this.id = id; return this; }
        public DeliveryRecordBuilder contract(Contract contract) { this.contract = contract; return this; }
        public DeliveryRecordBuilder deliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; return this; }
        public DeliveryRecordBuilder notes(String notes) { this.notes = notes; return this; }

        public DeliveryRecord build() {
            return new DeliveryRecord(id, contract, deliveryDate, notes);
        }
    }
}