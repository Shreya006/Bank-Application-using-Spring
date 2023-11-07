package com.fis.bankapplication.service;

import com.fis.bankapplication.model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {
	public String addTransaction(Transaction transaction);
    
	public String updateTransaction(Transaction transaction);
    
	public String deleteTransaction(long transactionId);
    
	public Transaction getTransactionById(long transactionId);
    
	public List<Transaction> getAllTransactions();
	
	public List<Transaction> getAccountStatementsByDateRange(long accountId,Date startDate,Date endDate);
	
//	public List<Transaction> getLastTwoTransactions(long accountId);
    
	public List<Transaction> getTransactionsForAccount(long accountId);
}