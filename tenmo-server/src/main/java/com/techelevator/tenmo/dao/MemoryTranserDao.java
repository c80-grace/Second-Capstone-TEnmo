package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public class MemoryTranserDao implements TransferDao {

    @Override
    public void transfer(Account accountFrom, Account accountTo, int amount) {
        if (accountFrom.getBalance() >= amount) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            System.out.println();
            System.out.println("Transfer Complete");
        }
        System.out.println();
        System.out.println("Not enough funds to transfer");
    }

}
