package com.techelevator.dao;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTransferTests extends BaseDaoTests {
    protected static final Transfer TRANSFER_1 = new Transfer(1, 2, 2, 2001, 2005, 500);


    private JdbcTransferDao sut;

    @Before
    public void setup(){
        this.sut = new JdbcTransferDao(new JdbcTemplate(dataSource), new JdbcAccountDao(new JdbcTemplate(dataSource)));
    }

    @Test
    public void transferFrom_returns_500_when_transfered_out_of_1(){
        sut.transferFrom(2001, 2005, 500);

        Assert.assertEquals(500, TRANSFER_1.getAmount(), 0.0 );
    }
    

}
