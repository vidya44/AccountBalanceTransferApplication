package com.cg.Exceptions;

public class InvalidPasswordLengthException extends RuntimeException{
    public InvalidPasswordLengthException(String message){
        super(message);
    }
}
