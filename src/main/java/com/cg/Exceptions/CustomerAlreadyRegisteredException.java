package com.cg.Exceptions;

public class CustomerAlreadyRegisteredException extends RuntimeException{
    public CustomerAlreadyRegisteredException(String message){
        super(message);
    }
}
