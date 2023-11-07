package com.fis.bankapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bankapplication.exceptions.CustomerNotFoundException;
import com.fis.bankapplication.model.Customer;
import com.fis.bankapplication.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody @Validated Customer customer) {
    	
//    	http://localhost:8080/customers/addCustomer    
        return customerService.addCustomer(customer);
    }

    @PutMapping("/updateCustomer")
    public String updateCustomer(@RequestBody @Validated Customer customer) {
    	
//    	http://localhost:8080/customers/updateCustomer    
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") long customerId) throws CustomerNotFoundException {
    	
//    	http://localhost:8080/customers/deleteCustomer/1   
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping("/getCustomer/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") long customerId) throws CustomerNotFoundException {
    	
//    	http://localhost:8080/customers/getCustomer/1 
        Customer customer = customerService.getCustomer(customerId);
        return customer;
        
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
    	
//    	http://localhost:8080/customers/getAllCustomers
        List<Customer> customers = customerService.getAllCustomers();
        return customers;
    }
}