package com.cg.Exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistEXception.class)
    public String handlerForUserAlreadyExistsException(UserAlreadyExistEXception e)
    {
        return "User already present with userName : "+ e.getMessage() ;
    }

    @ExceptionHandler(UsernameAndPasswordNotMathchesException.class)
    public String handlerForUsernameAndPasswordNotMathchesException(UsernameAndPasswordNotMathchesException e)
    {
        return "Username and password aren't matches: "+ e.getMessage() ;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handlerForUserNotFoundExceptionException(UserAlreadyExistEXception e)
    {
        return "User not found with userName : "+ e.getMessage() ;
    }
    @ExceptionHandler(CustomerAlreadyRegisteredException.class)
    public String handlerForCustomerAlreadyRegisteredException(CustomerAlreadyRegisteredException e)
    {
        return "Customer already registered : "+ e.getMessage() ;
    }
    @ExceptionHandler(InvalidDateOfBirthException.class)
    public String handlerForInvalidDateOfBirthExceptionException(InvalidDateOfBirthException e)
    {
        return "Date of birth should not be greater than 2010: "+ e.getMessage() ;
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public String handlerForCustomerNotFoundException(CustomerNotFoundException e)
    {
        return "Customer not found with customerId: "+ e.getMessage() ;
    }
    @ExceptionHandler(CustomerAlreadyExistException.class)
    public String handlerForCustomerAlreadyExistException(CustomerAlreadyExistException e)
    {
        return "Customer already registered with this account number: "+ e.getMessage() ;
    }
    @ExceptionHandler(AccountNotFoundException.class)
    public String handlerForAccountNotFoundException(AccountNotFoundException e)
    {
        return "Account not found with accountId: "+ e.getMessage() ;
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public String handlerForInsufficientBalanceException(InsufficientBalanceException e)
    {
        return "Insufficient balance for making transaction: "+ e.getMessage() ;
    }
    @ExceptionHandler(TransactionNotFoundException.class)
    public String handlerForTransactionNotFoundException(TransactionNotFoundException e)
    {
        return "Transaction Not Found With given Id: "+ e.getMessage() ;
    }

    @ExceptionHandler(InvalidMobileNumberException.class)
    public String handlerForInvalidMobileNumberException(InvalidMobileNumberException e)
    {
        return "Mobile number should be of 10 digits only. "+ e.getMessage() ;
    }
    @ExceptionHandler(InvalidAadhaarNumberException.class)
    public String handlerForInvalidAadhaarNumberException(InvalidAadhaarNumberException e)
    {
        return "Aadhaar number should be of 12 digits. "+ e.getMessage() ;
    }





}



