package com.example.Backend.controller;


import com.example.Backend.dto.CaseDTO;
import com.example.Backend.models.User;
import com.example.Backend.repository.UserRepository;
import com.example.Backend.service.CaseService;
import com.example.Backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;



    public UserController(UserRepository userRepository, UserService userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/users/{userId}/cases")
    public ResponseEntity<List<CaseDTO>> getCasesForUser(@PathVariable Integer userId){
        List<CaseDTO> cases = userService.getCasesForUser(userId);
        return ResponseEntity.ok(cases);
    }




}
