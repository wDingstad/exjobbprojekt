package com.example.Backend.service;


import com.example.Backend.dto.CaseDTO;
import com.example.Backend.dto.CaseProductDTO;
import com.example.Backend.dto.UserDTO;
import com.example.Backend.models.*;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.repository.ProductRepository;
import com.example.Backend.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {


    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final CaseRepository caseRepository;
    private final ProductRepository productRepository;


    public CaseService(JdbcTemplate jdbcTemplate, UserRepository userRepository, CaseRepository caseRepository, ProductRepository productRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.caseRepository = caseRepository;
        this.productRepository = productRepository;
    }



    public List<UserDTO> getUsersForCase(Integer caseId) {
        String sql = """
            SELECT u.id, u.first_name, u.last_name, u.email, u.phone_number
            FROM users u
            JOIN user_cases uc ON u.id = uc.user_id
            WHERE uc.case_id = ?
        """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new UserDTO(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number")
                ),
                caseId
        );
    }

    public void assignUser(Integer caseId, Integer userId){
        String sql = "INSERT INTO user_cases (user_id , case_id) VALUES (? , ?)";
        jdbcTemplate.update(sql, userId, caseId);

    }

    public void removeUser(Integer caseId, Integer userId){
        String sql = "DELETE FROM user_cases WHERE case_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, caseId, userId);

    }

    //public Case createCase(String case_name, String info, Integer customerId){
    //    Case newCase = new Case(case_name, info, customerId);
    //    return caseRepository.save(newCase);
    //}

    public CaseDTO createCase(String caseName, String info, Integer customerId){
        Case newCase = new Case(caseName, info, customerId);
        Case saved = caseRepository.save(newCase);
        return mapToDTO(saved);
    }

    //public Case updateStatus(Integer case_id, CaseStatus newStatus){
    //    Case foundCase = caseRepository.findById(case_id)
    //            .orElseThrow(() -> new RuntimeException("Case not found"));
    //    foundCase.setStatus(newStatus);
    //    return caseRepository.save(foundCase);

    public CaseDTO updateStatus(Integer caseId, CaseStatus newStatus){
        Case foundCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        foundCase.setStatus(newStatus);
        Case updated = caseRepository.save(foundCase);

        return mapToDTO(updated);
    }


    //public List <Case> getAllCases(){
    //    return caseRepository.findAll();
    //}
    public List<CaseDTO> getAllCasesDTO(){
        return caseRepository.findAll().stream().map(this::mapToDTO).toList();
    }

   // public Case getCaseById(Integer caseId) {
   //     return caseRepository.findById(caseId)
   //             .orElseThrow(() -> new RuntimeException("Case not found"));
   // }
   public CaseDTO getCaseDTOById(Integer caseId){
       Case foundCase = caseRepository.findById(caseId)
               .orElseThrow(() -> new RuntimeException("Case not found"));

       return mapToDTO(foundCase);
   }

    //public void deleteCase(Integer caseId) {
    //    if(!caseRepository.existsById(caseId)){
    //        throw new RuntimeException("Case not found");
    //    }
    //    caseRepository.deleteById(caseId);
    //}

    public void deleteCase(Integer caseId){
        if(!caseRepository.existsById(caseId)){
            throw new RuntimeException("Case not found");
        }
        caseRepository.deleteById(caseId);
    }

    //public Case updateCase(Integer id, Case updateCase){
    //    Case foundCase = caseRepository.findById(id)
    //            .orElseThrow(() -> new RuntimeException("Case not found"));
    //    foundCase.setCase_name(updateCase.getCase_name());
    //    foundCase.setInfo(updateCase.getInfo());
    //    foundCase.setStatus(updateCase.getStatus());
    //    return caseRepository.save(foundCase);
    //}
    public CaseDTO updateCase(Integer id, String caseName, String info, CaseStatus status){
        Case foundCase = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        foundCase.setCase_name(caseName);
        foundCase.setInfo(info);
        foundCase.setStatus(status);

        Case updated = caseRepository.save(foundCase);
        return mapToDTO(updated);
    }

    //public Case addProductToCase(Integer caseId, Integer articleNumber, Integer quantity){
    //    Case foundCase = caseRepository.findById(caseId)
    //            .orElseThrow(() -> new RuntimeException("Case not found"));

    //    Product foundProduct = productRepository.findByArticleNumber(articleNumber);
                //.orElseThrow(() -> new RuntimeException("Product not found"));

    //    CaseProduct caseProduct = new CaseProduct(foundCase, foundProduct, quantity);
    //    foundCase.getProducts().add(caseProduct);

    //    return caseRepository.save(foundCase);

    public CaseDTO addProductToCase(Integer caseId, Integer articleNumber, Integer quantity){
        Case foundCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        Product foundProduct = productRepository.findByArticleNumber(articleNumber);
        if(foundProduct == null){
            throw new RuntimeException("Product not found");
        }

        CaseProduct caseProduct = new CaseProduct(foundCase, foundProduct, quantity);
        foundCase.getProducts().add(caseProduct);

        Case saved = caseRepository.save(foundCase);
        return mapToDTO(saved);
    }

    private CaseDTO mapToDTO(Case caseObject){

        List<CaseProductDTO> productDTOs = caseObject.getProducts()
                .stream()
                .map(cp -> new CaseProductDTO(
                        cp.getProduct().getArticleNumber(),
                        cp.getProduct().getName(),
                        cp.getQuantity()
                ))
                .toList();

        return new CaseDTO(
                caseObject.getId(),
                caseObject.getCase_name(),
                caseObject.getInfo(),
                caseObject.getStatus().name(),
                caseObject.getCustomerId(),
                productDTOs
        );
    }






}
