package com.fis.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.fis.bankapplication.model.Transaction;
import com.fis.bankapplication.repository.TransactionRepo;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

	 @Autowired
	    private TransactionRepo transactionRepo;
	
	@Override
	public String addTransaction(Transaction transaction) {
		 
		return transactionRepo.addTransaction(transaction);
	}

	@Override
	public String updateTransaction(Transaction transaction) {
		 
		return transactionRepo.updateTransaction(transaction);
	}

	@Override
	public String deleteTransaction(long transactionId) {
		 
		return transactionRepo.deleteTransaction(transactionId);
	}

	@Override
	public Transaction getTransactionById(long transactionId) {
		 
		return transactionRepo.getTransactionById(transactionId);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		 
		return transactionRepo.getAllTransactions();
	}
	
	@Override
	public List<Transaction> getAccountStatementsByDateRange(long accountId,Date startDate,Date endDate){
		
		return transactionRepo.getAccountStatementsByDateRange(accountId, endDate, endDate);
		
	}
	
	@Override
	public List<Transaction> getLastTwoTransactions(long accountId){
		
		return transactionRepo.getLastTwoTransactions(accountId);
	}

	@Override
	public List<Transaction> getTransactionsForAccount(long accountId) {
		 
		return transactionRepo.getTransactionsForAccount(accountId);
	}

}
