package com.fis.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import com.fis.bankapplication.exceptions.CustomerNotFoundException;
import com.fis.bankapplication.model.Customer;
import com.fis.bankapplication.repository.CustomerRepo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
    private CustomerRepo customerRepo;

	@Override
	public String addCustomer(Customer customer) {
 
		customerRepo.save(customer);
		return "Customer added Successfully";
		
	}

	@Override
	public String updateCustomer(Customer customer) {
 
		customerRepo.save(customer);
		return "Customer updated Successfully";
	}

	@Override
	public String deleteCustomer(long customerId)
	{
 
		Customer customer = getCustomer(customerId);
				
		customerRepo.delete(customer);
        return "Customer Deleted Successfully";
	}

	@Override
	public Customer getCustomer(long customerId) throws CustomerNotFoundException 
	{
		Optional<Customer> optional = customerRepo.findById(customerId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found for : " + customerId);
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
 
		return customerRepo.findAll();
	}

}
