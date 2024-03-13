package com.cg.Exceptions;

public class InvalidAadhaarNumberException extends RuntimeException{
    public InvalidAadhaarNumberException(String message){
        super(message);
    }
}
