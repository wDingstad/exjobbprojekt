package com.example.Backend.service;

import com.example.Backend.dto.CaseDTO;
import com.example.Backend.models.Case;
import com.example.Backend.models.User;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.repository.UserRepository;
import com.example.Backend.exception.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;
    private final CaseRepository caseRepository;
    private final UserRepository userRepository;


    public UserService(JdbcTemplate jdbcTemplate, CaseRepository caseRepository, UserRepository userRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User updatedUser){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setFirst_name(updatedUser.getFirst_name());
        user.setLast_name(updatedUser.getLast_name());
        user.setEmail(updatedUser.getEmail());
        user.setPhone_number(updatedUser.getPhone_number());
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
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
                        rs.getString("status"),
                        rs.getInt("customer_id"),
                        List.of()
                ),
                userId
        );
    }


}
