package com.fis.bankapplication.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.fis.bankapplication.exceptions.CustomerNotFoundException;
import com.fis.bankapplication.model.Customer;


@Repository
public class CustomerRepoImpl implements CustomerRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String addCustomer(Customer customer) {
        try {
            entityManager.persist(customer);
            return "Customer added successfully";
        } catch (Exception e) {
            return "Error adding customer: " + e.getMessage();
        }
    }

    @Override
    public String updateCustomer(Customer customer) {
        try {
            entityManager.merge(customer);
            return "Customer updated successfully";
        } catch (Exception e) {
            return "Error updating customer: " + e.getMessage();
        }
    }

    @Override
    public String deleteCustomer(long customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if (customer != null) {
            entityManager.remove(customer);
            return "Customer deleted successfully";
        } else {
            return "Customer not found with ID: " + customerId;
        }
    }

    @Override
    public Customer getCustomer(long customerId) {
    	Customer customer = entityManager.find(Customer.class, customerId);

        if (customer == null) 
        {
            throw new CustomerNotFoundException("Customer not found for account number: " + customerId);
        }
        else {
        	return customer;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
                
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
		return query.getResultList();
    }
}