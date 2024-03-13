package com.cg.Services;

import com.cg.Model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    public Transaction sendMoney(String senderUserName, String receiverUserName, Long amount);
    public Transaction addMoney(String userName, Long amount);
    public Optional<Transaction> getTransactionById(Long transactionId);
    public List<Transaction> getAllTransactions();
    public List<Transaction> getTransactionsByUsername(String userName);
}
