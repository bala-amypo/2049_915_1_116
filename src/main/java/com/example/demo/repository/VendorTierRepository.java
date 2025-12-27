package com.example.demo.repository;

import com.example.demo.entity.VendorTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {
    Optional<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc();
    boolean existsByTierName(String tierName);
}