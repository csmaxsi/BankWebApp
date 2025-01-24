package com.bank.model;

import java.math.BigDecimal;

public class Account {
    private  String accType;
    private  BigDecimal balance;
    private Integer user_id;
    private  String accountNumber;


    public Account(String accType, BigDecimal initialDeposit, Integer user_id) {
        this.accType= accType;
        this.balance= initialDeposit;
        this.user_id= user_id;
    }

    public Account( String accountNumber, BigDecimal balance) {
        this.accountNumber= accountNumber;
        this.balance= balance;
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

}