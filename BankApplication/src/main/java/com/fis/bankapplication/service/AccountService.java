package com.fis.bankapplication.service;

import com.fis.bankapplication.model.Account;

import java.util.List;

public interface AccountService {
	
    public abstract String addAccount(Account account);
    
    public abstract String updateAccount(Account account);
    
    public abstract String deleteAccount(long accountId);
    
    public abstract Account getAccount(long accountId);
    
    public abstract List<Account> getAllAccounts();
    
    public abstract String deposit(long accountId, double amount);
    
    public abstract String withdraw(long accountId, double amount);
    
	public abstract String fundTransfer(long fromAccountNumber, long toAccountNumber, double amount, String transactionType);
	
    public abstract List<Account> getAccountsByCustomerId(long customerId);
	
    public abstract double getTotalBalanceByCustomerId(long customerId);
    
}