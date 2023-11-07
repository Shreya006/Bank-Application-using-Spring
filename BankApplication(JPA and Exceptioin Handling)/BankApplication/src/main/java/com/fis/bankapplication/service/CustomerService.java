package com.fis.bankapplication.service;

import com.fis.bankapplication.exceptions.CustomerNotFoundException;
import com.fis.bankapplication.model.Customer;

import java.util.List;

public interface CustomerService {
	
    public String addCustomer(Customer customer);

    public String updateCustomer(Customer customer);

    public String deleteCustomer(long customerId);

    public Customer getCustomer(long customerId) throws CustomerNotFoundException;

    public List<Customer> getAllCustomers();
}