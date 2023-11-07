package com.fis.bankapplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long id;

    @NotNull(message="Customer is required")
    @ManyToOne
    @JoinColumn(name = "customer_id")  //foreign key
    private Customer customer;

    @NotBlank(message = "Account type is required")
    @Column(name = "account_type")   
    private String accountType;

    @NotBlank(message = "Branch is required")
    @Column(name = "branch")   
    private String branch;

    @NotNull(message="Account Opening Date is required")
    @PastOrPresent(message = "Please enter a valid Account opening date")
    @Column(name = "account_open_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date accountOpenDate;

    @NotBlank(message = "Password is required")
    @Column(name = "password")   
    private String password;

    @NotBlank(message = "Balance is required")
    @Column(name = "balance")   
    private double balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(Date accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public String getPassword() {
        return password;
    }

	public Account( @NotNull(message = "Customer is required") Customer customer,
			@NotBlank(message = "Account type is required") String accountType,
			@NotBlank(message = "Branch is required") String branch,
			@NotNull(message = "Account Opening Date is required") @PastOrPresent(message = "Please enter a valid Account opening date") Date accountOpenDate,
			@NotBlank(message = "Password is required") String password,
			@NotBlank(message = "Balance is required") double balance) {
		super();
//		this.id = id;
		this.customer = customer;
		this.accountType = accountType;
		this.branch = branch;
		this.accountOpenDate = accountOpenDate;
		this.password = password;
		this.balance = balance;
	}

	public Account() {
		super();
	}

	public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    

    @Override
	public String toString() {
		return "Account [id=" + id + ", customer=" + customer + ", accountType=" + accountType + ", branch=" + branch
				+ ", accountOpenDate=" + accountOpenDate + ", password=" + password + ", balance=" + balance + "]";
	}

}