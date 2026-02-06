package com.example.Backend.controller;


import com.example.Backend.models.Case;
import com.example.Backend.repository.CaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CaseController {

    private final CaseRepository caseRepository;


    public CaseController(CaseRepository caseRepository){
        this.caseRepository = caseRepository;
    }

    @GetMapping("/cases")
    public List<Case> getAllCases(){
        return caseRepository.findAll();
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity<Case> findCaseById(@PathVariable Integer id){
        return caseRepository.findById(id)
                .map(foundcase -> ResponseEntity.ok().body(foundcase))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/cases")
    public ResponseEntity<Case> createCase(@RequestBody Case newCase){
        Case savedCase = caseRepository.save(newCase);
        return ResponseEntity.ok(savedCase);
    }

}
