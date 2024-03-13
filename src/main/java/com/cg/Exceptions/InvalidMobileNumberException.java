package com.cg.Exceptions;

public class InvalidMobileNumberException extends RuntimeException{
    public InvalidMobileNumberException(String message){
        super(message);
    }
}
