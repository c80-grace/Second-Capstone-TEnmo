package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface TransferDao {

    void transfer(Account accountFrom, Account accountTo, int amount);

}
