package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_records")
public class DeliveryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    private String notes;

    public DeliveryRecord() {}

    public DeliveryRecord(Contract contract, LocalDate deliveryDate, String notes) {
        this.contract = contract;
        this.deliveryDate = deliveryDate;
        this.notes = notes;
    }

    // Builder pattern
    public static DeliveryRecordBuilder builder() {
        return new DeliveryRecordBuilder();
    }

    public static class DeliveryRecordBuilder {
        private Long id;
        private Contract contract;
        private LocalDate deliveryDate;
        private String notes;

        public DeliveryRecordBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DeliveryRecordBuilder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public DeliveryRecordBuilder deliveryDate(LocalDate deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public DeliveryRecordBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public DeliveryRecord build() {
            DeliveryRecord record = new DeliveryRecord();
            record.id = this.id;
            record.contract = this.contract;
            record.deliveryDate = this.deliveryDate;
            record.notes = this.notes;
            return record;
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}