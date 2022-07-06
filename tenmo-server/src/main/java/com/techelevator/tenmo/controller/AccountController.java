package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Account;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    private AccountDao accountDao;


    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;

    }
    @RequestMapping(path = "/accounts/{id}", method = RequestMethod.GET)
    public Account get(@PathVariable int id) {
        return accountDao.get(id);
   }

    @RequestMapping(path = "/accounts/{id}/balance", method = RequestMethod.GET)
    public double getBalance(@PathVariable int id) {
        return accountDao.getBalance(id);
    }

    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public List<Account> list() {
        return accountDao.list();
    }
}
