package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDao implements TransferDao {

    private List<Account> accounts = new ArrayList<>();
//Update to SQL terms
    @Override
    public void transfer(int accountFrom, int accountTo, int amount) throws UserNotFoundException {

        for (Account accountF : accounts) {
            if (accountF.getAccountId()== accountFrom) {
                for (Account accountT : accounts)
                if (accountT.getAccountId() == accountTo) {
                    if (accountF.getBalance() >= amount) {
                        accountF.setBalance(accountF.getBalance() - amount);
                        accountT.setBalance(accountT.getBalance() + amount);
                        System.out.println();
                        System.out.println("Transfer Complete");
                    }
                }
                throw new UserNotFoundException();
            }
            throw new UserNotFoundException();
        }

        System.out.println();
        System.out.println("Not enough funds to transfer");
    }

}
