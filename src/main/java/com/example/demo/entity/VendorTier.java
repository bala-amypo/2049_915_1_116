package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendor_tiers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorTier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tier_name")
    private String tierName;
    
    @Column(name = "tier_level")
    private Integer tierLevel;
}