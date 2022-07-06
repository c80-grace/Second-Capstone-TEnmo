package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {

    double findBalance(int accountId);

    Account get(int accountId);

    double getBalance(int accountId);

    List<Account> list();


}
