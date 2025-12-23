package com.example.demo.entity;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRecord {
    private Long id;
    private Long contractId;
    private int expectedQuantity;
    private int deliveredQuantity;
    private LocalDate deliveryDate;
}
