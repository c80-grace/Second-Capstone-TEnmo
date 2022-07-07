package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    void transferFrom(int accountFrom, int accountTo, int amount);


    List<Transfer> findAll();

    Transfer findByTransferId(int transferId);

}
