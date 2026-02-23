package com.example.Backend.repository;


import com.example.Backend.models.Case;
import com.example.Backend.models.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CaseRepository extends JpaRepository<Case, Integer> {

    // SELECT * FROM cases WHERE customerId= ?
    //spring gör sql kommandon från metodnamn sjukt nice :))

    List<Case> findByCustomerId(Integer customerId);
    List<Case> findByStatus(CaseStatus status);
    List<Case> findByCustomerIdAndStatus(Integer customerId, CaseStatus status);


}
