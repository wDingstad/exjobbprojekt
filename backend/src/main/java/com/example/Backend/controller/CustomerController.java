package com.example.Backend.controller;


import com.example.Backend.models.Case;
import com.example.Backend.models.Customer;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.repository.CustomerRepository;
import com.example.Backend.service.CustomerService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    //private final CustomerRepository customerRepository;
    //private final CaseRepository caseRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CaseRepository caseRepository, CustomerService customerService){
        //this.customerRepository = customerRepository;
        //this.caseRepository = caseRepository;
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }


    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PostMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomer));
    }

    @DeleteMapping ("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}/cases")
    public ResponseEntity <List<Case>> getCasesForCustomer(@PathVariable Integer customerId){
        return ResponseEntity.ok(customerService.getCasesForCustomer(customerId));



    }



}
