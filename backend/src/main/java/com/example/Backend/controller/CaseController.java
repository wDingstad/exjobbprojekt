package com.example.Backend.controller;


import com.example.Backend.dto.*;
import com.example.Backend.models.Case;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.service.CaseService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Backend.dto.AddProductToCase;


import java.util.List;

@RestController
//@RequestMapping("/cases") //kan lägga till detta för at minska på /cases kod repetitioin
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService){
        this.caseService = caseService;
    }

    @GetMapping("/cases")
    public ResponseEntity<List<CaseDTO>> getAllCases(){
        return ResponseEntity.ok(caseService.getAllCasesDTO());
    }
    //public List<Case> getAllCases(){
    //    return caseService.getAllCases();
    //}

    @GetMapping("/cases/{id}")
    public ResponseEntity<CaseDTO> findCaseById(@PathVariable Integer id){
        return ResponseEntity.ok(caseService.getCaseDTOById(id));
    }
    //public ResponseEntity<CaseDTO> findCaseById(@PathVariable Integer id){
    //    Case foundCase = caseService.getCaseById(id);
    //    return ResponseEntity.ok(foundCase);
    //}


    @PostMapping("/cases")
    public ResponseEntity<CaseDTO> createCase(@RequestBody Case newCase){
        CaseDTO createdCase = caseService.createCase(
                newCase.getCase_name(),
                newCase.getInfo(),
                newCase.getCustomerId()
        );
        return ResponseEntity.ok(createdCase);
    }
    //public ResponseEntity<CaseDTO> createCase(@RequestBody Case newCase){
    //    Case savedCase = caseService.createCase(newCase.getCase_name(), newCase.getInfo(), newCase.getCustomerId());
    //    return ResponseEntity.ok(savedCase);
    //}


    @PutMapping("/cases/{id}")
    public ResponseEntity<CaseDTO> updateCase(
            @PathVariable Integer id,
            @RequestBody Case updateCase  // tar emot Case direkt
    ){
        // Anropar service och returnerar CaseDTO
        CaseDTO updatedCase = caseService.updateCase(
                id,
                updateCase.getCase_name(),  // snake_case bibehållen
                updateCase.getInfo(),
                updateCase.getStatus()      // CaseStatus enum
        );

        return ResponseEntity.ok(updatedCase);
    }
    //public ResponseEntity<CaseDTO> updateCase(@PathVariable Integer id, @RequestBody Case updateCase){
    //    Case updated = caseService.updateCase(id, updateCase);
    //    return ResponseEntity.ok(updated);
    //}

    @DeleteMapping("/cases/{id}")
    //public ResponseEntity<?> deleteCase(@PathVariable Integer id){
    //    caseService.deleteCase(id);
    //    return ResponseEntity.ok().build();
    //}
    public ResponseEntity<Void> deleteCase(@PathVariable Integer id){
        caseService.deleteCase(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/cases/{caseId}/users")
    //public ResponseEntity<?> assignUserToCase(@PathVariable Integer caseId, @RequestBody AssignUserRequest request){
    //    caseService.assignUser(caseId, request.getUserId());
    //    return ResponseEntity.ok().build();
    //}
    public ResponseEntity<Void> assignUserToCase(
            @PathVariable Integer caseId,
            @RequestBody AssignUserRequest request){

        caseService.assignUser(caseId, request.getUserId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cases/{caseId}/users")
    //public ResponseEntity<List<UserDTO>> getUsersForCase(@PathVariable Integer caseId){
    //    List<UserDTO> users = caseService.getUsersForCase(caseId);
    //    return ResponseEntity.ok(users);
    //}
    public ResponseEntity<List<UserDTO>> getUsersForCase(@PathVariable Integer caseId){
        return ResponseEntity.ok(caseService.getUsersForCase(caseId));
    }

    @DeleteMapping("/cases/{caseId}/users/{userId}")
    //public ResponseEntity<?> removeUserFromCase(@PathVariable Integer caseId, @PathVariable Integer userId){
    //    caseService.removeUser(caseId, userId);
    //    return ResponseEntity.ok().build();
    //}

    public ResponseEntity<Void> removeUserFromCase(
            @PathVariable Integer caseId,
            @PathVariable Integer userId){

        caseService.removeUser(caseId, userId);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping ("/cases/{id}/status")
    public ResponseEntity<CaseDTO> updateStatus(
            @PathVariable Integer id,
            @RequestBody UpdateStatusRequest request){

        return ResponseEntity.ok(
                caseService.updateStatus(id, request.getStatus())
        );
    }
    //public ResponseEntity<CaseDTO> updateStatus(@PathVariable Integer id, @RequestBody UpdateStatusRequest request){
    //    Case updated = caseService.updateStatus(id, request.getStatus());
    //    return ResponseEntity.ok(updated);
    //}


    @PostMapping("/cases/{caseId}/products")
    //public ResponseEntity<CaseDTO> addProductToCase(@PathVariable Integer caseId, @RequestBody AddProductToCase request){
    //    Case updatedCase = caseService.addProductToCase(caseId, request.getArticleNumber(), request.getQuantity());
    //    return ResponseEntity.ok(updatedCase);
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
