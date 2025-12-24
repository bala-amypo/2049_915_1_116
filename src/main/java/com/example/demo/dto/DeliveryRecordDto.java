package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRecordDto {

    private Long contractId;
    private LocalDate deliveryDate;
    private String notes;
}
