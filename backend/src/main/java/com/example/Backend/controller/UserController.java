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
       return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));

    }

    @GetMapping("/users/{userId}/cases")
    public ResponseEntity<List<CaseDTO>> getCasesForUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getCasesForUser(userId));
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updateUser){
        return ResponseEntity.ok(userService.updateUser(id, updateUser));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }




}
