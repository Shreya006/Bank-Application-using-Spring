package com.fis.bankapplication.repository;

import java.util.Date;
import java.util.List;

import com.fis.bankapplication.model.Transaction;

public interface TransactionRepo {
	public abstract String addTransaction(Transaction transaction);
    
	public abstract String updateTransaction(Transaction transaction);
    
	public abstract String deleteTransaction(long transactionId);
    
	public abstract Transaction getTransactionById(long transactionId);
    
	public abstract List<Transaction> getAllTransactions();
	    
	public abstract List<Transaction> getAccountStatementsByDateRange(long accountId,Date startDate,Date endDate);
	
	public abstract List<Transaction> getLastTwoTransactions(long accountId);
	
	public abstract List<Transaction> getTransactionsForAccount(long accountId);
}