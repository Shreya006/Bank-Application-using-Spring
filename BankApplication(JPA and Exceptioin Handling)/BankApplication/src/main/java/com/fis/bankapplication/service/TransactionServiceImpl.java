package com.fis.bankapplication.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fis.bankapplication.exceptions.TransactionNotFoundException;
import com.fis.bankapplication.model.Transaction;
import com.fis.bankapplication.repository.TransactionRepo;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

	 @Autowired
	private TransactionRepo transactionRepo;
	
	@Override
	public String addTransaction(Transaction transaction) {
		 
		transactionRepo.save(transaction);
		return "Transaction added Successfully";
	}

	@Override
	public String updateTransaction(Transaction transaction) {
		 
		transactionRepo.save(transaction);
		return "Transaction added Successfully";
	}

	@Override
	public String deleteTransaction(long transactionId) {
		 
		transactionRepo.deleteById(transactionId);
		return "Transaction deleted Successfully";
	}

	@Override
	public Transaction getTransactionById(long transactionId) throws TransactionNotFoundException
	{
		Optional<Transaction> optional = transactionRepo.findById(transactionId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new TransactionNotFoundException("Transaction not found for : " + transactionId);
		}
	}

	@Override
	public List<Transaction> getAllTransactions() {
		 
		return transactionRepo.findAll();
	}
	
	@Override
	public List<Transaction> getAccountStatementsByDateRange(long accountId,Date startDate,Date endDate){
		
		return transactionRepo.getAccountStatementsByDateRange(accountId, endDate, endDate);
		
	}
	
//	@Override
//	public List<Transaction> getLastTwoTransactions(long accountId){
//		
//		return transactionRepo.getLastTwoTransactions(accountId);
//	}

	@Override
	public List<Transaction> getTransactionsForAccount(long accountId) {
		 
		return transactionRepo.getTransactionsForAccount(accountId);
	}

}
