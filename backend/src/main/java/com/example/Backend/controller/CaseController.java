package com.example.Backend.controller;


import com.example.Backend.dto.AssignUserRequest;
import com.example.Backend.dto.UserDTO;
import com.example.Backend.models.Case;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.service.CaseService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/cases") //kan lägga till detta för at minska på /cases kod repetitioin
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService){
        this.caseService = caseService;
    }

    @GetMapping("/cases")
    public List<Case> getAllCases(){
        return caseService.getAllCases();
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity<Case> findCaseById(@PathVariable Integer id){
        Case foundCase = caseService.getCaseById(id);
        return ResponseEntity.ok(foundCase);
    }

    @PostMapping("/cases")
    public ResponseEntity<Case> createCase(@RequestBody Case newCase){
        Case savedCase = caseService.createCase(newCase.getCase_name(), newCase.getInfo(), newCase.getCustomerId());
        return ResponseEntity.ok(savedCase);
    }

    @PutMapping("/cases/{id}")
    public ResponseEntity<Case> updateCase(@PathVariable Integer id, @RequestBody Case updateCase){
        Case updated = caseService.updateCase(id, updateCase);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/cases/{id}")
    public ResponseEntity<?> deleteCase(@PathVariable Integer id){
        caseService.deleteCase(id);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<?> removeUserFromCase(@PathVariable Integer caseId, @PathVariable Integer userId){
        caseService.removeUser(caseId, userId);
        return ResponseEntity.ok().build();
    }
}