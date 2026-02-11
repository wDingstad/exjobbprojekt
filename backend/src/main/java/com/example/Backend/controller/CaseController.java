package com.example.Backend.controller;


import com.example.Backend.dto.AssignUserRequest;
import com.example.Backend.dto.UserDTO;
import com.example.Backend.models.Case;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/cases") //kan lägga till detta för at minska på /cases kod repetitioin
public class CaseController {

    private final CaseRepository caseRepository;
    private final CaseService caseService;


    public CaseController(CaseRepository caseRepository, CaseService caseService){
        this.caseRepository = caseRepository;
        this.caseService = caseService;
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


    @PostMapping("/cases/{caseId}/users")
    public ResponseEntity<?> assignUserToCase(@PathVariable Integer caseId, @RequestBody AssignUserRequest request){
        caseService.assignUser(caseId, request.getUserId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cases/{caseId}/users")
    public ResponseEntity<List<UserDTO>> getUsersForCase(@PathVariable Integer caseId){
        List<UserDTO> users = caseService.getUsersForCase(caseId);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/cases/{caseId}/users/{userId}")
    public ResponseEntity<?> removeUserFromCase(
            @PathVariable Integer caseId,
            @PathVariable Integer userId) {

        caseService.removeUser(caseId, userId);
        return ResponseEntity.ok().build();

    }
}
