package com.fis.bankapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id") 
    private long id;

    @ManyToOne
    @JoinColumn(name = "account_from_id", referencedColumnName = "account_id")
//    private long accountFrom;
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_to_id", referencedColumnName = "account_id") 
//    private long accountTo;
    private Account accountTo;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date_of_transaction")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dateOfTransaction;

    @Column(name = "transaction_type") 
    private String transactionType;


	public Transaction(Account accountFrom, Account accountTo, double amount, String transactionType, Date dateOfTransaction) {
//		this.id = id;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
		this.transactionType = transactionType;
		this.dateOfTransaction = dateOfTransaction;
	}

	public Account getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(Account accountFrom) {
		this.accountFrom = accountFrom;
	}

	public Account getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}