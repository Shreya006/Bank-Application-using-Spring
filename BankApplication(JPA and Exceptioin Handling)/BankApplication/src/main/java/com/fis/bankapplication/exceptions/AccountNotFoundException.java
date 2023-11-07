package com.fis.bankapplication.exceptions;

public class AccountNotFoundException extends RuntimeException 
{
    public AccountNotFoundException(String message) 
    {
        super(message);
    }
}