package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MemoryAccountDao implements AccountDao {

    private List<Account> accounts = new ArrayList<>();

    @Override
    public double findBalance(int accountId) throws UserNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account.getBalance();
            }
        }
        throw new UserNotFoundException();
    }
}