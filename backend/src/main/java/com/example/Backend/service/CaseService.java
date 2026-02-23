package com.example.Backend.service;


import com.example.Backend.dto.UserDTO;
import com.example.Backend.models.Case;
import com.example.Backend.models.CaseStatus;
import com.example.Backend.models.User;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {


    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final CaseRepository caseRepository;


    public CaseService(JdbcTemplate jdbcTemplate, UserRepository userRepository, CaseRepository caseRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.caseRepository = caseRepository;
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

    public Case createCase(String case_name, String info, Integer customerId){
        Case newCase = new Case(case_name, info, customerId);
        return caseRepository.save(newCase);
    }
    public Case updateStatus(Integer case_id, CaseStatus newStatus){
        Case foundCase = caseRepository.findById(case_id)
                .orElseThrow(() -> new RuntimeException("Case not found"));
        foundCase.setStatus(newStatus);
        return caseRepository.save(foundCase);
    }

    public List <Case> getAllCases(){
        return caseRepository.findAll();
    }

    public Case getCaseById(Integer caseId) {
        return caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));
    }

    public void deleteCase(Integer caseId) {
        if(!caseRepository.existsById(caseId)){
            throw new RuntimeException("Case not found");
        }
        caseRepository.deleteById(caseId);
    }

    public Case updateCase(Integer id, Case updateCase){
        Case foundCase = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found"));
        foundCase.setCase_name(updateCase.getCase_name());
        foundCase.setInfo(updateCase.getInfo());
        foundCase.setStatus(updateCase.getStatus());
        return caseRepository.save(foundCase);
    }




}
