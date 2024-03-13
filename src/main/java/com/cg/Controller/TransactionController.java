package com.cg.Controller;

import com.cg.Exceptions.AccountNotFoundException;
import com.cg.Exceptions.InsufficientBalanceException;
import com.cg.Exceptions.TransactionNotFoundException;
import com.cg.Model.Transaction;
import com.cg.Services.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("transactionController")
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("/sendMoney")
    public ResponseEntity<Transaction> sendMoney(@RequestParam String senderUserName,
                                                 @RequestParam String receiverUserName,
                                                 @RequestParam Long amount) {
        try {
            Transaction transaction = transactionService.sendMoney(senderUserName, receiverUserName, amount);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (AccountNotFoundException e) {
            System.out.println("Account not found with given user name:");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InsufficientBalanceException e) {
            System.out.println("Not enough balance for making transaction:");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addMoney")
    public ResponseEntity<Transaction> addMoney(@RequestParam String userName,
                                                @RequestParam Long amount) {
        try {
            // Check if the amount is positive
            if (amount <= 0) {
                System.out.println("Ammount should be positive value:");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Transaction transaction = transactionService.addMoney(userName, amount);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (AccountNotFoundException e) {
            System.out.println("No associated account found with userName:");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTransactionByTransactionId/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId) {
        try {
            Optional<Transaction> transaction = transactionService.getTransactionById(transactionId);

            if (transaction.isPresent()) {
                return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
            } else {
                System.out.println("Transaction not found with given transactionId:");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getTransactionsByUsername/{userName}")
    public ResponseEntity<List<Transaction>> getTransactionsByUsername(@PathVariable String userName) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsByUsername(userName);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (TransactionNotFoundException e) {
            System.out.println("Transaction not found with given userName:");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
