package com.fis.bankapplication.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fis.bankapplication.model.Account;
import com.fis.bankapplication.model.Transaction;

//import com.fis.bankapplication.repository.TransactionRepo;

@Repository
@Transactional
public class AccountRepoImpl implements AccountRepo {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public String addAccount(Account account) {
        entityManager.persist(account);
        return "Account added successfully";
    }

    @Override
    public String updateAccount(Account account) {
        entityManager.merge(account);
        return "Account updated successfully";
    }

    @Override
    public String deleteAccount(long accountId) {
        Account account = entityManager.find(Account.class, accountId);
        if (account != null) {
            entityManager.remove(account);
            return "Account deleted successfully";
        }
        return "Account not found";
    }

    @Override
    public Account getAccount(long accountId) {
        return entityManager.find(Account.class, accountId);
    }

    @Override
    public List<Account> getAllAccounts() {
        String jpql = "SELECT a FROM Account a";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        return query.getResultList();
    }
    
    @Override
	public String deposit(long accountId, double amount) {
    	Account account = entityManager.find(Account.class, accountId);

        if (account != null) {
            if (amount > 0) {
                double currentBalance = account.getBalance();
                double newBalance = currentBalance + amount;
                account.setBalance(newBalance);

                entityManager.merge(account);
                
                Transaction depositTransaction = new Transaction(account, account, amount, "Deposit", new Date());
                
                transactionRepo.addTransaction(depositTransaction);      

                return "Deposit of " + amount + " successful. New balance: " + newBalance;
            } else {
                return "Invalid deposit amount. Please provide a positive amount.";
            }
        } else {
            return "Account not found with account number: " + accountId;
        }
	}
    
    
    @Override
	public String withdraw(long accountId, double amount) {
    	Account account = entityManager.find(Account.class, accountId);

        if (account != null) {
            if (amount > 0) {
                double currentBalance = account.getBalance();
                double newBalance = currentBalance - amount;
                account.setBalance(newBalance);
                
                entityManager.merge(account);
                
                Transaction withdrawTransaction = new Transaction(account, account, amount, "Withdraw", new Date());
                
                transactionRepo.addTransaction(withdrawTransaction);           


                return "Withdraw of " + amount + " successful. New balance: " + newBalance;
            } else {
                return "Invalid withdraw amount. Please provide a positive amount.";
            }
        } else {
            return "Account not found with account number: " + accountId;
        }
	}
    
    
    @Override
	public String fundTransfer(long fromAccountNumber, long toAccountNumber, double amount, String transactionType) {
    	Account fromAccount = entityManager.find(Account.class, fromAccountNumber);
        Account toAccount = entityManager.find(Account.class, toAccountNumber);

        if (fromAccount == null) {
            return "Source account not found";
        }

        if (toAccount == null) {
            return "Destination account not found";
        }

        if (fromAccount.getBalance() < amount) {
            return "Insufficient funds for the transfer";
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        entityManager.merge(fromAccount);
        entityManager.merge(toAccount);

        Transaction fromTransaction = new Transaction(fromAccount, toAccount, amount, transactionType, new Date());
        Transaction toTransaction = new Transaction(fromAccount, toAccount, amount, transactionType, new Date());

        transactionRepo.addTransaction(fromTransaction);    
        transactionRepo.addTransaction(toTransaction);    

        return "Funds transferred successfully";
	}

	@Override
	public List<Account> getAccountsByCustomerId(long customerId) {
		 String jpql = "SELECT a FROM Account a WHERE a.customer.customerId = ?1";
	     TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
	     query.setParameter(1, customerId);
	     return query.getResultList();
	}

	@Override
	public double getTotalBalanceByCustomerId(long customerId) {
		 String jpql = "SELECT SUM(a.balance) FROM Account a WHERE a.customer.customerId = ?1";
	     TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
	     query.setParameter(1, customerId);
	     return query.getSingleResult();
	}
      }