package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MemoryAccountDao implements AccountDao {

    private List<Account> accounts = new ArrayList<>();


    public MemoryAccountDao(){
        if (accounts.size()==0) {
            setAccounts();
        }
    }

    @Override
    public double findBalance(int accountId) throws UserNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account.getBalance();
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public Account get(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

    @Override
    public double getBalance(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account.getBalance();
            }
        }
        return -1;
    }

    @Override
    public List<Account> list() {
        return accounts;
    }

    private void setAccounts() {
        accounts.add(new Account(1, 1));
        accounts.add(new Account(2, 2));
        accounts.add(new Account(3, 3));
    }
}