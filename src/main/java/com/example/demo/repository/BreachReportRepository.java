package com.example.demo.repository;

import com.example.demo.entity.BreachReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BreachReportRepository extends JpaRepository<BreachReport, Long> {

    List<BreachReport> findByContractId(Long contractId);

    Optional<BreachReport> findById(Long id);

    List<BreachReport> findAll();
}
