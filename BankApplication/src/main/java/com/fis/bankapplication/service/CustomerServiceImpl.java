package com.fis.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.fis.bankapplication.model.Customer;
import com.fis.bankapplication.repository.CustomerRepo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
    private CustomerRepo customerRepo;

	@Override
	public String addCustomer(Customer customer) {
 
		return customerRepo.addCustomer(customer);
	}

	@Override
	public String updateCustomer(Customer customer) {
 
		return customerRepo.updateCustomer(customer);
	}

	@Override
	public String deleteCustomer(long customerId) {
 
		return customerRepo.deleteCustomer(customerId);
	}

	@Override
	public Customer getCustomer(long customerId) {
 
		return customerRepo.getCustomer(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
 
		return customerRepo.getAllCustomers();
	}

}
