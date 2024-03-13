package com.cg.Model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
   @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
   private Long amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime addMoneyTimestamp;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendMoneyTimestamp;
    @ManyToOne
    @JoinColumn(name = "account_number")
    private Customer customer;

    public Transaction(){
        super();
    }
    public Transaction(Long transactionId, TransactionType transactionType, Long amount,
                       LocalDateTime addMoneyTimestamp,LocalDateTime sendMoneyTimestamp,Customer customer) {
       this.transactionId=transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.addMoneyTimestamp=addMoneyTimestamp;
        this.sendMoneyTimestamp=sendMoneyTimestamp;
        this.customer=customer;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getAddMoneyTimestamp() {
        return addMoneyTimestamp;
    }

    public void setAddMoneyTimestamp(LocalDateTime addMoneyTimestamp) {
        this.addMoneyTimestamp = addMoneyTimestamp;
    }

    public LocalDateTime getSendMoneyTimestamp() {
        return sendMoneyTimestamp;
    }

    public void setSendMoneyTimestamp(LocalDateTime sendMoneyTimestamp) {
        this.sendMoneyTimestamp = sendMoneyTimestamp;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
