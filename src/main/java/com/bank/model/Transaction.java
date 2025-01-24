package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public class Transaction {
    private int accountId;
    private String transactionType;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    // Constructor
    public Transaction(int accountId, BigDecimal amount, String transactionType) {
        this.accountId = accountId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = LocalDateTime.now(); // Set timestamp to the current time
    }


    public int getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {

        return amount;
    }

    public String getTransactionType() {

        return transactionType;
    }

    public LocalDateTime getTimestamp() {

        return timestamp;
    }
}
