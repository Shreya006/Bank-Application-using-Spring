package com.fis.bankapplication.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bankapplication.model.Transaction;
import com.fis.bankapplication.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addTransaction")
    public String createTransaction(@RequestBody Transaction transaction) {
    	
//    	http://localhost:8080/transactions/addTransaction 
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("/getTransaction/{transactionId}")
    public Transaction getTransaction(@PathVariable("transactionId") long transactionId) {
    	
//    	http://localhost:8080/transactions/getTransaction/1 
        Transaction transaction = transactionService.getTransactionById(transactionId);
        return transaction;
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getAccountStatementsByDateRange(
            @PathVariable("accountId") long accountId,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
//          once check below yyyy-MM-dd
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) {
    	
//    	http://localhost:8080/transactions/account/1 
        List<Transaction> accountStatements = transactionService.getAccountStatementsByDateRange(accountId, startDate, endDate);
        return accountStatements;
    }


    
    @GetMapping("/account/{accountId}/allTransactions")
    public List<Transaction> getTransactionsForAccount(@PathVariable("accountId") long accountId) {
    	
//    	http://localhost:8080/transactions/account/1/allTransactions
        List<Transaction> allTransactions = transactionService.getTransactionsForAccount(accountId);
        return allTransactions;
    }
}