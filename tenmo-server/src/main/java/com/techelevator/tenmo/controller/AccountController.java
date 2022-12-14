package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {
    private AccountDao accountDao;


    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;

    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/accounts/{id}", method = RequestMethod.GET)
    public Account get(@PathVariable int id) {
        return accountDao.get(id);
   }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/accounts/{id}/balance", method = RequestMethod.GET)
    public double getBalance(@PathVariable int id) {
        return accountDao.getBalance(id);
    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public List<Account> list() {
        return accountDao.list();
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/account/{userId}", method = RequestMethod.GET)
    public Account getAccountFromUserId(@PathVariable int userId) {
        System.out.println(userId);
        return accountDao.getAccountByUserId(userId);
    }

}
