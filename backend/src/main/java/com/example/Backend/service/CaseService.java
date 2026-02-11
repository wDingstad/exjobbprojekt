package com.example.Backend.service;


import com.example.Backend.dto.UserDTO;
import com.example.Backend.models.User;
import com.example.Backend.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {


    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;


    public CaseService(JdbcTemplate jdbcTemplate, UserRepository userRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
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



}
