package com.fis.bankapplication.exceptions;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String message) 
    {
        super(message);
    }
	
}