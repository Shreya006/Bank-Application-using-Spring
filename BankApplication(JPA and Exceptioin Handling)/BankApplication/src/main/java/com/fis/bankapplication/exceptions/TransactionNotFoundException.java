package com.fis.bankapplication.exceptions;

public class TransactionNotFoundException extends RuntimeException {
	public TransactionNotFoundException(String message) 
    {
        super(message);
    }
}