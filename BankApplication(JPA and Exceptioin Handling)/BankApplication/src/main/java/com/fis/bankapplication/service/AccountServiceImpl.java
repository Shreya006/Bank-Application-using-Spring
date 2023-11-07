package com.fis.bankapplication.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.fis.bankapplication.exceptions.AccountNotFoundException;
import com.fis.bankapplication.model.Account;
import com.fis.bankapplication.model.Transaction;
import com.fis.bankapplication.repository.AccountRepo;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;
    
    @Autowired
    private TransactionService transactionService;

    @Override
    public String addAccount(Account account) {

        accountRepo.save(account);
        return "Account added Successfully";
    }
    
    @Override
    public String updateAccount(Account account) {

    	accountRepo.save(account);
        return "Account updated Successfully";
    }

    
    @Override
    public String deleteAccount(long accountId)
    {
    	Account account = getAccount(accountId);
		
    	accountRepo.delete(account);
        return "Account Deleted Successfully";
    }


    @Override
    public Account getAccount(long accountId) throws AccountNotFoundException
    {
    	Optional<Account> optional = accountRepo.findById(accountId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new AccountNotFoundException("Account not found for : " + accountId);
		}
        
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
    
    @Override
    public String deposit(long accountId, double amount) {
        Account account = getAccount(accountId);

        if (amount > 0) {
//            double currentBalance = account.getBalance();
//            double newBalance = currentBalance + amount;
//            account.setBalance(newBalance);
//
//            System.out.println("account" + account); 
//            
//            accountRepo.save(account);
//            
////            updateAccount(account);
        	
        	accountRepo.deposit(accountId, amount);
        	
            Transaction depositTransaction = new Transaction(account, account, amount, "Deposit", new Date());

            transactionService.addTransaction(depositTransaction);
            
            double newBalance = account.getBalance() + amount;

            return "Deposit of " + amount + " successful. New balance: " + newBalance;
            
//            return "Deposit of " + amount + " successful. New balance: " + newBalance;
        } else {
            return "Invalid deposit. Please provide a valid account and amount.";
        }
    }
    
    @Override
    public String withdraw(long accountId, double amount) {
    	 Account account = getAccount(accountId);

        if (amount > 0 && account.getBalance() >= amount) {
//            double currentBalance = account.getBalance();
//            double newBalance = currentBalance - amount;
//            account.setBalance(newBalance);
//
//            accountRepo.save(account);
        	
        	accountRepo.withdraw(accountId, amount);        	     	
        	
            Transaction withdrawTransaction = new Transaction(account, account, amount, "Withdraw", new Date());

            transactionService.addTransaction(withdrawTransaction);
            
            double newBalance = account.getBalance() - amount;
            
            return "Withdraw of " + amount + " successful. New balance: " + newBalance;
        } else {
            return "Invalid withdrawal. Please provide a valid account, amount, and ensure sufficient balance.";
        }
    }
    
    @Override
   	public String fundTransfer(long fromAccountNumber, long toAccountNumber, double amount, String transactionType) {
    	Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);
        
        if (amount > 0 && fromAccount.getBalance() >= amount) {
//            double fromAccountBalance = fromAccount.getBalance();
//            double toAccountBalance = toAccount.getBalance();
//
//            double newFromAccountBalance = fromAccountBalance - amount;
//            double newToAccountBalance = toAccountBalance + amount;
//
//            fromAccount.setBalance(newFromAccountBalance);
//            toAccount.setBalance(newToAccountBalance);
//
//            accountRepo.save(fromAccount);
//            accountRepo.save(toAccount);
        	
        	accountRepo.fundTransferFrom(fromAccountNumber, amount);
        	accountRepo.fundTransferTo(toAccountNumber, amount);
        		
            
            Transaction fromTransaction = new Transaction(fromAccount, toAccount, amount, transactionType, new Date());
            Transaction toTransaction = new Transaction(fromAccount, toAccount, amount, transactionType, new Date());

            transactionService.addTransaction(fromTransaction);
            transactionService.addTransaction(toTransaction);
            
            double newBalanceFromAccount = fromAccount.getBalance() - amount;
            double newBalanceToAccount = toAccount.getBalance() + amount;

            return "Fund transfer of " + amount + " successful. New balance for sender: " + newBalanceFromAccount + ", new balance for receiver: " + newBalanceToAccount;
        } else {
            return "Invalid fund transfer. Please provide valid sender and receiver accounts, amount, and ensure sufficient balance in the sender's account.";
        }
    }
    

    @Override
    public List<Account> getAccountsByCustomerId(long customerId) {
        return accountRepo.findByCustomer_Id(customerId);
    }
    
    @Override
    public double getTotalBalanceByCustomerId(long customerId) {
        double totalBalance = accountRepo.sumBalanceByCustomerId(customerId);
        return totalBalance;
  }

    
}