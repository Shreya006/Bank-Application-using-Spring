package com.fis.bankapplication.service;

import com.fis.bankapplication.model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {
	public abstract String addTransaction(Transaction transaction);
    
	public abstract String updateTransaction(Transaction transaction);
    
	public abstract String deleteTransaction(long transactionId);
    
	public abstract Transaction getTransactionById(long transactionId);
    
	public abstract List<Transaction> getAllTransactions();
	
	public abstract List<Transaction> getAccountStatementsByDateRange(long accountId,Date startDate,Date endDate);
	
	public abstract List<Transaction> getLastTwoTransactions(long accountId);
    
	public abstract List<Transaction> getTransactionsForAccount(long accountId);
}