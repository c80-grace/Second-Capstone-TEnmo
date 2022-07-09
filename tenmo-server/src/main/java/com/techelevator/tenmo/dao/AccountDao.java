package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {

    Account get(int accountId);

    double getBalance(int accountId);

    List<Account> list();

    Account getAccountByUserId(int userId);

    Account getUserByAccountId(int accountId);



}
