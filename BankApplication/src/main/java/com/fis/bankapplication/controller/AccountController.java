package com.fis.bankapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bankapplication.model.Account;
import com.fis.bankapplication.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/addAccount")
    public String addAccount(@RequestBody Account account) {
    	
//    	http://localhost:8080/accounts/addAccount    	
        return accountService.addAccount(account);
    }

    @PutMapping("/updateAccount")
    public String updateAccount(@RequestBody Account account) {
    	
//    	http://localhost:8080/accounts/updateAccount
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/deleteAccount/{accountId}")
    public String deleteAccount(@PathVariable("accountId") long accountId) {
    	
//    	http://localhost:8080/accounts/deleteAccount/1
        return accountService.deleteAccount(accountId);
    }

    @GetMapping("/getAccount/{accountId}")
    public Account getAccount(@PathVariable("accountId") long accountId) {
    	
//    	http://localhost:8080/accounts/getAccount/1
        Account account = accountService.getAccount(accountId);
            return account;
    }

    @GetMapping("/getAllAccounts")
    public List<Account> getAllAccounts() {
    	
//    	http://localhost:8080/accounts/getAllAccounts
        List<Account> accounts = accountService.getAllAccounts();
        return accounts;
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("accountId") long accountId, @RequestParam("amount") double amount) {
    	
//    	http://localhost:8080/accounts/deposit
    	String response = accountService.deposit(accountId, amount);
    	return response;
    }
    
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("accountId") long accountId, @RequestParam("amount") double amount) {
    	
//    	http://localhost:8080/accounts/withdraw
    	String response = accountService.withdraw(accountId, amount);
    	return response;
    }
    
    @PostMapping("/fundTransfer")
    public String fundTransfer(@RequestParam("fromAccountNumber") long fromAccountNumber, @RequestParam("toAccountNumber") long toAccountNumber, @RequestParam("amount") double amount, @RequestParam("transactionType") String transactionType) {
        
//    	http://localhost:8080/accounts/fundTransfer
    	String response = accountService.fundTransfer(fromAccountNumber, toAccountNumber, amount, transactionType);
        return response;
    }
    
    @GetMapping("/by-customer/{customerId}")
    
//	http://localhost:8080/accounts/by-customer/1
    public List<Account> getAccountsByCustomerId(@PathVariable("customerId") long customerId) {
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        return accounts;
    }


    @GetMapping("/total-balance/{customerId}")
    
//	http://localhost:8080/accounts/total-balance/1
    public double getTotalBalanceByCustomerId(@PathVariable("customerId") long customerId) {
        double totalBalance = accountService.getTotalBalanceByCustomerId(customerId);
        return totalBalance;
    }
}