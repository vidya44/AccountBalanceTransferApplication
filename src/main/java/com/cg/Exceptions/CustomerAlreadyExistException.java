package com.cg.Exceptions;

public class CustomerAlreadyExistException extends RuntimeException{
    public CustomerAlreadyExistException(String message){
        super(message);
    }
}
