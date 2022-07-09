package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;
    private final AccountDao accountDao;
    private List<Account> accounts = new ArrayList<>();

    public JdbcTransferDao(JdbcTemplate jdbcTemplate, AccountDao accountDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountDao = accountDao;
    }



    @Override
    public Transfer transferFrom(int accountFrom, int accountTo, double  amount) {

        //accountTo --> accountNumber


         double balanceFrom = accountDao.getBalance(accountFrom);
         double balanceTo = accountDao.getBalance(accountTo);
        if (balanceFrom >= amount)  {
            if (accountFrom != accountTo) {
                String sql = "INSERT INTO tenmo_transfer (transfer_type_id, " +
                        "transfer_status_id, account_from, account_to, amount) " +
                        "VALUES (?, ?, ?, ?, ?) " +
                        "RETURNING transfer_id;";
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql, 2, 2, accountFrom, accountTo, amount);

                sql = "UPDATE tenmo_account " +
                        "SET balance = ? WHERE account_id = ?";
                jdbcTemplate.update(sql, (balanceFrom - amount), accountFrom);
                sql = "UPDATE tenmo_account " +
                        "SET balance = ? WHERE account_id = ?";
                jdbcTemplate.update(sql, (balanceTo + amount), accountTo);
                if (results.next()) {
                    return mapRowToTransfer(results);
                }
            } else {
                System.out.println("Cannot send money to yourself, try again.");
                throw new InnacurateAmountException();
            }
        } else {
            System.out.println("Sorry, insufficient funds.");
            throw new InnacurateAmountException();
        }
        return null;
    }



    @Override
    public List<Transfer> findAll() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM tenmo_transfer";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public Transfer findByTransferId(int transferId) throws TransferNotFoundException {
        String sql = "SELECT * FROM tenmo_transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()) {
            return mapRowToTransfer(results);
        } else {
            throw new TransferNotFoundException();
        }
    }

    @Override
    public Transfer findUsernameByAccountId(int accountId) {
        String sql ="SELECT username FROM tenmo_transfer " +
                "LEFT JOIN tenmo_account ON tenmo_transfer.account_to = tenmo_account.account_id " +
                "LEFT JOIN tenmo_user USING (user_id) " +
                "WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if (results.next()) {
            return mapRowToTransfer(results);
        } else {
            throw new UserNotFoundException();
        }
    }


    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getDouble("amount"));
        return transfer;
    }

}
