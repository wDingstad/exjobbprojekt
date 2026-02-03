package com.example.Backend.repository;
import com.example.Backend.models.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TestUserRepository extends JpaRepository<TestUser, Long>{

}
