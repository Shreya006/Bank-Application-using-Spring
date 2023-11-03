package com.fis.bankapplication.repository;

import com.fis.bankapplication.model.Customer;

import java.util.List;

public interface CustomerRepo {
	
    public abstract String addCustomer(Customer customer);

    public abstract String updateCustomer(Customer customer);

    public abstract String deleteCustomer(long customerId);

    public abstract Customer getCustomer(long customerId);

    public abstract List<Customer> getAllCustomers();
}