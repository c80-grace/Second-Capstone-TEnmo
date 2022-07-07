package com.techelevator.tenmo.model;

import javax.validation.constraints.NotNull;

public class Account {
    @NotNull
    private int accountId;
    @NotNull
    private int userId;
    private double balance = 1000;

    public Account(int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;

    }
    public Account(){

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
