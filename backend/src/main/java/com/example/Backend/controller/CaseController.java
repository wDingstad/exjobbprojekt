package com.example.Backend.controller;


import com.example.Backend.dto.*;
import com.example.Backend.models.Case;
import com.example.Backend.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService){
        this.caseService = caseService;
    }

    @GetMapping("/cases")
    public ResponseEntity<List<CaseDTO>> getAllCases(){
        return ResponseEntity.ok(caseService.getAllCasesDTO());
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity<CaseDTO> findCaseById(@PathVariable Integer id){
        return ResponseEntity.ok(caseService.getCaseDTOById(id));
    }

    @PostMapping("/cases")
    public ResponseEntity<CaseDTO> createCase(@RequestBody Case newCase){
        CaseDTO createdCase = caseService.createCase(
                newCase.getCase_name(),
                newCase.getInfo(),
                newCase.getCustomerId()
        );
        return ResponseEntity.ok(createdCase);
    }

    @PutMapping("/cases/{id}")
    public ResponseEntity<CaseDTO> updateCase(
            @PathVariable Integer id,
            @RequestBody Case updateCase
    ){
        CaseDTO updatedCase = caseService.updateCase(
                id,
                updateCase.getCase_name(),
                updateCase.getInfo(),
                updateCase.getStatus()
        );
        return ResponseEntity.ok(updatedCase);
    }

    @DeleteMapping("/cases/{id}")
    public ResponseEntity<Void> deleteCase(@PathVariable Integer id){
        caseService.deleteCase(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cases/{caseId}/users")
    public ResponseEntity<Void> assignUserToCase(
            @PathVariable Integer caseId,
            @RequestBody AssignUserRequest request){
        caseService.assignUser(caseId, request.getUserId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cases/{caseId}/users")
    public ResponseEntity<List<UserDTO>> getUsersForCase(@PathVariable Integer caseId){
        return ResponseEntity.ok(caseService.getUsersForCase(caseId));
    }

    @DeleteMapping("/cases/{caseId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromCase(
            @PathVariable Integer caseId,
            @PathVariable Integer userId){
        caseService.removeUser(caseId, userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/cases/{id}/status")
    public ResponseEntity<CaseDTO> updateStatus(
            @PathVariable Integer id,
            @RequestBody UpdateStatusRequest request){
        return ResponseEntity.ok(
                caseService.updateStatus(id, request.getStatus())
        );
    }

    @PostMapping("/cases/{caseId}/products")
    public ResponseEntity<CaseDTO> addProductToCase(
            @PathVariable Integer caseId,
            @RequestBody AddProductToCase request){
        return ResponseEntity.ok(
                caseService.addProductToCase(
                        caseId,
                        request.getArticleNumber(),
                        request.getQuantity()
                )
        );
    }
}
