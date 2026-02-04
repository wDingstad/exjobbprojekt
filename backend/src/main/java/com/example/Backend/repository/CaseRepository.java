package com.example.Backend.repository;


import com.example.Backend.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CaseRepository extends JpaRepository<Case, Integer> {
}
