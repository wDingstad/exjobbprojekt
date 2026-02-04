package com.example.Backend.repository;


import com.example.Backend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
