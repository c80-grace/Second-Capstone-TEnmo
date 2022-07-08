package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AccountNotFoundException;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class JdbcAccountDaoTests extends BaseDaoTests {
    private static final Account ACCOUNT_1 = new Account(2001, 1001, 1000);
    protected static final Account ACCOUNT_2 = new Account(2005, 1002, 1000);
    private static final Account ACCOUNT_3 = new Account(2009, 1003, 1000);

    private JdbcAccountDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDao(jdbcTemplate);
    }

    @Test(expected = AccountNotFoundException.class)
    public void get_given_0_throws_exception() {
        sut.get(0);
    }

    @Test
    public void get_given_2001_returns_account_1() {
        Account actualAccount = sut.get(ACCOUNT_1.getAccountId());

        Assert.assertEquals(ACCOUNT_1.getAccountId(), actualAccount.getAccountId());
    }

    @Test
    public void getBalance_given_2001_returns_1000() {
        double actualBalance = sut.getBalance(ACCOUNT_1.getAccountId());

        Assert.assertEquals(1000, actualBalance, 0.0);
    }

    @Test
    public void list_returns_all_accounts() {
        List<Account> accounts = sut.list();

        Assert.assertNotNull(accounts);
        Assert.assertEquals(3, accounts.size());
        Assert.assertEquals(ACCOUNT_1, accounts.get(0));
        Assert.assertEquals(ACCOUNT_2, accounts.get(1));
        Assert.assertEquals(ACCOUNT_3, accounts.get(2));
    }


}
