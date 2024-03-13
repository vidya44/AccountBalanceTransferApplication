package com.cg.Services;

import com.cg.Exceptions.InsufficientBalanceException;
import com.cg.Exceptions.TransactionNotFoundException;
import com.cg.Exceptions.UserNotFoundException;
import com.cg.Model.Customer;
import com.cg.Model.Transaction;
import com.cg.Model.TransactionType;
import com.cg.Repository.CustomerRepository;
import com.cg.Repository.TransactionRepository;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("TransactionService")
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
   CustomerServiceImpl customerService;
    @Autowired
    TransactionRepository transactionRepository;
    @Enumerated
    TransactionType transactionType;

    @Override
    public Transaction sendMoney(String senderUserName, String receiverUserName, Long amount) {
        // Retrieve sender and receiver accounts
        Customer senderUser = customerService.getCustomerByUsername(senderUserName);
                if(senderUser==null){
                   throw  new UserNotFoundException("Sender account not found with userName: " + senderUserName);
                }

        Customer receiverUser = customerService.getCustomerByUsername(receiverUserName);
                if(receiverUser==null){
                    throw new UserNotFoundException("Receiver account not found with userName: " + receiverUserName);
                }

        // Check if sender has sufficient balance
        if (senderUser.getAccountBalance() < amount) {
            throw new InsufficientBalanceException("Sender does not have sufficient balance to perform the transaction");
        }

        // Update balances
        senderUser.setAccountBalance(senderUser.getAccountBalance() - amount);
        receiverUser.setAccountBalance(receiverUser.getAccountBalance()+ amount);

        // Create and save transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.SEND_MONEY);
        transaction.setAmount(amount);
        transaction.setSendMoneyTimestamp(LocalDateTime.now());
        transaction.setCustomer(senderUser);
        transactionRepository.save(transaction);

        return transaction;
    }

    @Override
    public Transaction addMoney(String userName, Long amount) {
        // Retrieve the user
        Customer customer = customerService.getCustomerByUsername(userName);
                if (customer==null){
                    throw new UserNotFoundException("User not found with userId: " + userName);
                }

        // Update the account balance
        customer.setAccountBalance(customer.getAccountBalance() + amount);

        // Save the updated user
        customerRepository.save(customer);

        // Create and save transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.ADD_MONEY);
        transaction.setAmount(amount);
        transaction.setAddMoneyTimestamp(LocalDateTime.now());
        transaction.setCustomer(customer);
        transactionRepository.save(transaction);

        return transaction;
    }

    @Override
    public Optional<Transaction> getTransactionById(Long transactionId) {
        Optional<Transaction> transaction=transactionRepository.findById(transactionId);
        if(transaction.isPresent()){
            return transaction;
        }
        else {
            throw new TransactionNotFoundException("No transaction found with given transaction Id:" +transactionId);
        }
    }
    @Override
    public List<Transaction> getTransactionsByUsername(String userName) {
        List<Transaction> transactions = transactionRepository.findTransactionsByUserName(userName);
        if (!transactions.isEmpty()) {
            return transactions;
        } else {
            throw new TransactionNotFoundException("No transactions found for the given user Id: " + userName);
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
       List<Transaction> t=transactionRepository.findAll();
       return t;
    }



}
