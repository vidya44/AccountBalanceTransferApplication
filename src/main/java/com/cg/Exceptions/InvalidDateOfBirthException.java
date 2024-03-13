package com.cg.Exceptions;

public class InvalidDateOfBirthException extends RuntimeException{
    public InvalidDateOfBirthException(String message){
        super(message);
    }
}
