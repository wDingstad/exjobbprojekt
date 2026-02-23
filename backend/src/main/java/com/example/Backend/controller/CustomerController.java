package com.example.Backend.controller;


import com.example.Backend.models.Case;
import com.example.Backend.models.Customer;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.repository.CustomerRepository;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CaseRepository caseRepository;


    public CustomerController(CustomerRepository customerRepository, CaseRepository caseRepository){
        this.customerRepository = customerRepository;
        this.caseRepository = caseRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        return customerRepository.findById(id)
                .map(customer -> ResponseEntity.ok().body(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PostMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer){
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setCustomer_name(updatedCustomer.getCustomer_name());
                    customer.setOrganisation_number(updatedCustomer.getOrganisation_number());
                    customer.setCustomer_email(updatedCustomer.getCustomer_email());
                    customer.setCustomer_number(updatedCustomer.getCustomer_number());
                    Customer saved = customerRepository.save(customer);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping ("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id){
        return customerRepository.findById(id)
                .map(customer ->{
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{customerId}/cases")
    public ResponseEntity <List<Case>> getCasesForCustomer(@PathVariable Integer customerId){
        List<Case> cases = caseRepository.findByCustomerId(customerId);
        return ResponseEntity.ok(cases);
    }



}
