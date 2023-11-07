package com.fis.bankapplication.service;

import com.fis.bankapplication.model.Account;
import com.fis.bankapplication.exceptions.AccountNotFoundException;

import java.util.List;

public interface AccountService {
	
    public String addAccount(Account account);
    
    public String updateAccount(Account account);
    
    public String deleteAccount(long accountId);
    
    public Account getAccount(long accountId) throws AccountNotFoundException;
    
    public List<Account> getAllAccounts();
    
    public String deposit(long accountId, double amount);
    
    public String withdraw(long accountId, double amount);
    
	public String fundTransfer(long fromAccountNumber, long toAccountNumber, double amount, String transactionType);
	
    public List<Account> getAccountsByCustomerId(long customerId);
	
    public double getTotalBalanceByCustomerId(long customerId);
    
}