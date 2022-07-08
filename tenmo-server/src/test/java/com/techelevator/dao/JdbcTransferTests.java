package com.techelevator.dao;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.InnacurateAmountException;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class JdbcTransferTests extends BaseDaoTests {
    protected static final Transfer TRANSFER_1 = new Transfer(3001, 2, 2, 2001, 2005, 500);
    protected static final Transfer TRANSFER_2 = new Transfer(3002, 2, 2, 2009, 2001, 500);

    private JdbcTransferDao sut;

    @Before
    public void setup(){
        this.sut = new JdbcTransferDao(new JdbcTemplate(dataSource), new JdbcAccountDao(new JdbcTemplate(dataSource)));
    }

    @Test
    public void transferFrom_returns_500_when_transferred_out_of_1(){
        sut.transferFrom(2001, 2005, 500);

        Assert.assertEquals(500, TRANSFER_1.getAmount(), 0.0 );
    }

    @Test (expected = InnacurateAmountException.class)
    public void transferFrom_returns_exception_when_9000_transferred_out_of_1(){
        sut.transferFrom(2001, 2005, 9000);
    }

    @Test (expected = InnacurateAmountException.class)
    public void transferFrom_returns_exception_when_sent_to_self(){
        sut.transferFrom(2001, 2001, 500);
    }

    @Test
    public void findAll_returns_all_transfers() {
        List<Transfer> tranfers = sut.findAll();

        Assert.assertNotNull(tranfers);
        Assert.assertEquals(2, tranfers.size());
        Assert.assertEquals(TRANSFER_1, tranfers.get(0));
        Assert.assertEquals(TRANSFER_2, tranfers.get(1));
    }

    @Test
    public void findByTransferId_returns_transfer1_when_given_3001(){
        Transfer realTransfer = sut.findByTransferId(TRANSFER_1.getTransferId());

        Assert.assertEquals(TRANSFER_1, realTransfer);
    }

    @Test (expected = TransferNotFoundException.class)
    public void findByTransferId_returns_exception_given_wrong_id(){
        sut.findByTransferId(9000000);
    }
}
