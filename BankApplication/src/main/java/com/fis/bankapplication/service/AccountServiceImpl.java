package com.fis.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.fis.bankapplication.model.Account;
import com.fis.bankapplication.repository.AccountRepo;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public String addAccount(Account account) {

        return accountRepo.addAccount(account);
    }

    @Override
    public String updateAccount(Account account) {

    	return accountRepo.updateAccount(account);
    }

    @Override
    public String deleteAccount(long accountId) {
    	
        return accountRepo.deleteAccount(accountId);
    }

    @Override
    public Account getAccount(long accountId) {
    	
        return accountRepo.getAccount(accountId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.getAllAccounts();
    }
    
    
    @Override
    public String deposit(long accountId, double amount) {
    	return accountRepo.deposit(accountId, amount);
    }
    
    @Override
    public String withdraw(long accountId, double amount) {
    	return accountRepo.withdraw(accountId, amount);
    }
    
    @Override
   	public String fundTransfer(long fromAccountNumber, long toAccountNumber, double amount, String transactionType) {
    	return accountRepo.fundTransfer(fromAccountNumber, toAccountNumber, amount, transactionType);
    }
    

    @Override
    public List<Account> getAccountsByCustomerId(long customerId) {
        return accountRepo.getAccountsByCustomerId(customerId);
    }
    
    @Override
    public double getTotalBalanceByCustomerId(long customerId) {
        double totalBalance = accountRepo.getTotalBalanceByCustomerId(customerId);
        return totalBalance;
  }
    
}