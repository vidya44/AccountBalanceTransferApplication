package com.cg.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Customer {

    @Id
    @NotNull(message = "Account number must not be null")
    @Digits(integer = 10, fraction = 0, message = "Account number should be 10 digits")
    @Column(name = "account_number", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 1000000000, allocationSize = 1)
    private Long accountNumber;

    @Column
    @NotBlank(message = "Customer name is required")
    private String customerName;
//    @Column
//    @NotBlank(message = "Last name is required")
//    private String lastName;
    @NotNull(message = "Aadhaar number must not be null")
    @Digits(integer = 12, fraction = 0, message = "Aadhaar number should be 12 digits")
    @Column(name = "aadhaar_number", unique = true)
    private Long aadhaarNumber;
    @Column(unique = true)
    @NotBlank(message = "Mobile number is required")
    @Digits(integer = 10, fraction = 0, message = "Aadhaar number should be 10 digits")
    @Pattern(regexp = "^[0-9]*$" ,message = "Mobile number should only contain digits")
    private String mobileNumber;
   // @ValidDateOfBirth
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "user_name", nullable = false, unique = true)
    @JsonProperty("userName")
    private String userName;
    @Column(nullable = false)
    @Length(min = 8, message = "Password should be a minimum of 8 characters.")
    @Pattern(regexp = "^[A-Z].*", message = "Password should start with a capital letter.")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime logoutTime;

    @Column(nullable = false)
    private Double accountBalance=20000.0;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id" )
//    private Transaction transaction;
    public Customer(){
        super();
    }

    public Customer(Long accountNumber, String customerName,Long aadhaarNumber, String mobileNumber, LocalDate dateOfBirth, String userName,
                    String password, LocalDateTime loginTime, LocalDateTime logoutTime, Double accountBalance) {
        this.accountNumber = accountNumber;
        this.customerName=customerName;
        this.aadhaarNumber = aadhaarNumber;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.userName = userName;
        this.password = password;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.accountBalance = accountBalance;
        //this.transaction = transaction;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

//    public Transaction getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(Transaction transaction) {
//        this.transaction = transaction;
//    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getAadhaarNumber() {
        return aadhaarNumber;
    }
    public void setAadhaarNumber(Long aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
