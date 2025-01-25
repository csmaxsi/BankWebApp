package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {
    private  String accType;
    private  BigDecimal balance;
    private Integer user_id;
    private  String accountNumber;
    private BigDecimal interestRate; // New field for interest rate
    private LocalDate maturityDate;

    public Account(String accType, BigDecimal initialDeposit, Integer user_id,BigDecimal interestRate) {
        this.accType= accType;
        this.balance= initialDeposit;
        this.user_id= user_id;
        this. interestRate = interestRate;
    }

    public Account( String accountNumber, BigDecimal balance,BigDecimal interestRate) {
        this.accountNumber= accountNumber;
        this.balance= balance;
        this.interestRate= interestRate;
    }

    public Account(String accountNumber, BigDecimal balance) {
        this.accountNumber= accountNumber;
        this.balance= balance;
    }

    public Account(String accountId, LocalDate maturityDate, BigDecimal interestEarned) {
        this.accountNumber = accountId;
        this.maturityDate = maturityDate;
        this.interestRate = interestEarned;

    }

    public Account(String accountId) {
        this.accountNumber= accountId;
    }

    public String getAccType() {

        return accType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return user_id;
    }
    public String getAccountId() {
        return accountNumber;
    }
    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}