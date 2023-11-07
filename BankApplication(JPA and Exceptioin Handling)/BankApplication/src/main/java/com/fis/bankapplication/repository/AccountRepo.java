package com.fis.bankapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fis.bankapplication.model.Account;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<Account,Long> {
	
//	DSL Grammar	
	
//	deposit	
//	@Query("select p from Product p where p.productPrice between ?1 and ?2")	
	@Modifying
	@Query("UPDATE Account a SET a.balance = a.balance + :amount WHERE a.id = :accountId")
	void deposit(@Param("accountId") long accountId, @Param("amount") double amount);
	
//	withdraw
	@Modifying
	@Query("UPDATE Account a SET a.balance = a.balance - :amount WHERE a.id = :accountId")
	void withdraw(@Param("accountId") long accountId, @Param("amount") double amount);
	
//	fund transfer
	@Modifying
	@Query("UPDATE Account fromAccount SET fromAccount.balance = fromAccount.balance - :amount WHERE fromAccount.id = :fromAccountNumber")
	void fundTransferFrom(@Param("fromAccountNumber") long fromAccountNumber, @Param("amount") double amount);
	
	@Modifying
	@Query("UPDATE Account toAccount SET toAccount.balance = toAccount.balance + :amount WHERE toAccount.id = :toAccountNumber")
	void fundTransferTo(@Param("toAccountNumber") long toAccountNumber, @Param("amount") double amount);
		
//	SELECT a FROM Account a WHERE a.customer.id = ?1
	public List<Account> findByCustomer_Id  (long customerId);
    
//	SELECT SUM(a.balance) FROM Account a WHERE a.customer.id = ?1
	@Query("SELECT SUM(a.balance) FROM Account a WHERE a.customer.id = :customerId")
	public double sumBalanceByCustomerId(@Param("customerId") long customerId);

}