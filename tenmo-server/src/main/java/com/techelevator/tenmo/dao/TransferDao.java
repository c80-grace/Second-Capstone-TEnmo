package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface TransferDao {

    void transfer(int accountFrom, int accountTo, int amount);

}
