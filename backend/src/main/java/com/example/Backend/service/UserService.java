package com.example.Backend.service;

import com.example.Backend.dto.CaseDTO;
import com.example.Backend.models.Case;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;
    private final CaseRepository caseRepository;


    public UserService(JdbcTemplate jdbcTemplate, CaseRepository caseRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.caseRepository = caseRepository;
    }

    public List<CaseDTO> getCasesForUser(Integer userId){

        String sql = """
        SELECT c.id, c.case_name, c.info, c.customer_id, c.status
        FROM cases c 
        JOIN user_cases uc ON c.id = uc.case_id
        WHERE uc.user_id = ?
    """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new CaseDTO(
                        rs.getInt("id"),
                        rs.getString("case_name"),
                        rs.getString("info"),
                        rs.getInt("customer_id"),
                        rs.getString("status")
                ),
                userId
        );
    }


}
