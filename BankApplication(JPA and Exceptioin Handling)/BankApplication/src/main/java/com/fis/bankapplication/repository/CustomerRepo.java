package com.fis.bankapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fis.bankapplication.model.Customer;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	
}