package com.example.Backend.controller;


import com.example.Backend.models.TestUser;
import com.example.Backend.repository.TestUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestUserController {

    private final TestUserRepository testUserRepository;

    public TestUserController(TestUserRepository testUserRepository) {
        this.testUserRepository = testUserRepository;
    }


    @GetMapping("/test_user")
    public List<TestUser> getTestUser(){
        return testUserRepository.findAll();
    }

}
