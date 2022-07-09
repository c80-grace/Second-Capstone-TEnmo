package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AccountNotFoundException;
import com.techelevator.tenmo.model.UserNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {
    private final JdbcTemplate jdbcTemplate;
    private List<Account> accounts = new ArrayList<>();


    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public double getBalance(int accountId) {
        new Account();
        Account newAccount;
        String sql = "SELECT * FROM tenmo_account WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if (results.next()) {
            newAccount = mapRowToAccount(results);
            return newAccount.getBalance();
        } else {
            throw new AccountNotFoundException();
        }
    }


    @Override
    public Account get(int accountId)  {
        String sql = "Select * FROM tenmo_account WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if (results.next()) {
            return mapRowToAccount(results);
        } else {
            throw new AccountNotFoundException();
        }
    }




    @Override
    public List<Account> list() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM tenmo_account";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Account account = mapRowToAccount(results);
            accounts.add(account);
        }

        return accounts;
    }

    @Override
    public Account getAccountByUserId(int userId) {
        String sql = "SELECT * FROM tenmo_account WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToAccount(results);
        } else {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public Account getUserByAccountId(int accountId) {
        String sql = "SELECT * FROM tenmo_account WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if (results.next()) {
            return mapRowToAccount(results);
        } else {
            throw new UserNotFoundException();
        }
    }


    private void setAccounts() {
        accounts.add(new Account(1, 1, 1000));
        accounts.add(new Account(2, 2, 1000));
        accounts.add(new Account(3, 3, 1000));
    }


    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setUserId(rs.getInt("user_id"));
        account.setAccountId(rs.getInt("account_id"));
        account.setBalance(rs.getDouble("balance"));

        return account;
    }
}