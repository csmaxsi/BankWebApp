package com.bank;

import java.math.BigDecimal;

public class Account {
    private final String accType;
    private final BigDecimal balance;
    private final Integer user_id;


    public Account(String accType, BigDecimal initialDeposit, Integer user_id) {
        this.accType= accType;
        this.balance= initialDeposit;
        this.user_id= user_id;
    }

    public String getAccType() {

        return accType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getId() {
        return user_id;
    }

}