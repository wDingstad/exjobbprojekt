package com.example.Backend.service;

import com.example.Backend.controller.CustomerController;
import com.example.Backend.models.Case;
import com.example.Backend.models.Customer;
import com.example.Backend.repository.CaseRepository;
import com.example.Backend.repository.CustomerRepository;

import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CaseRepository caseRepository;


    public CustomerService(CustomerRepository customerRepository, CaseRepository caseRepository){
        this.caseRepository = caseRepository;
        this.customerRepository = customerRepository;

    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        customer.setCustomer_number(updatedCustomer.getCustomer_number());
        customer.setCustomer_name(updatedCustomer.getCustomer_name());
        customer.setCustomer_email(updatedCustomer.getCustomer_email());
        customer.setOrganisation_number(updatedCustomer.getOrganisation_number());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id){
        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    public List<Case> getCasesForCustomer(Integer customerId) {
        return caseRepository.findByCustomerId(customerId);
    }

}
