package com.example.Backend.controller;


import com.example.Backend.models.Case;
import com.example.Backend.repository.CaseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
