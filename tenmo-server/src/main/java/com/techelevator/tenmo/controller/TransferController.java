package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private TransferDao transferDao;

    public TransferController(TransferDao transferDao){
        this.transferDao = transferDao;
    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/transfers/{accountFrom}/{accountTo}/{amount}", method = RequestMethod.PUT)
    public void transferFrom(@PathVariable int accountFrom,@PathVariable int accountTo,@PathVariable double amount){
            transferDao.transferFrom(accountFrom, accountTo, amount);
    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/transfers", method = RequestMethod.GET)
    public List<Transfer> findAll() {
        return transferDao.findAll();
    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/transfers/{transferId}", method = RequestMethod.GET)
    public Transfer findByTransferId(int transferId) {
        return transferDao.findByTransferId(transferId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/{accountId}/transfers", method = RequestMethod.GET)
    public Transfer findUsernameByAccountId(@PathVariable int accountId) {
        return transferDao.findUsernameByAccountId(accountId);
    }

}