package com.fis.bankapplication.service;

import com.fis.bankapplication.model.Customer;

import java.util.List;

public interface CustomerService {
	
    public abstract String addCustomer(Customer customer);

    public abstract String updateCustomer(Customer customer);

    public abstract String deleteCustomer(long customerId);

    public abstract Customer getCustomer(long customerId);

    public abstract List<Customer> getAllCustomers();
}