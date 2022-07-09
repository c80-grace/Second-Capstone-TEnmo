package com.techelevator.tenmo.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.tenmo.Exceptions.BadRequest;
import com.techelevator.tenmo.Exceptions.BadRequestException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.util.BasicLogger;
import org.apiguardian.api.API;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class AccountService {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

//    public Account addAccount(Account newAccount) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Account> entity = new HttpEntity<>(newAccount, headers);
//
//        Account returnedAccount = null;
//        try {
//            returnedAccount = restTemplate.postForObject(API_BASE_URL + "accounts", entity, Account.class);
//        } catch (ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        } catch (RestClientResponseException e) {
//            BasicLogger.log(e.getMessage());
//
//            if (e.getRawStatusCode() == 400) {
//                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                try {
//                    BadRequest badRequest = mapper.readValue(e.getResponseBodyAsByteArray(), BadRequest.class);
//                    throw new BadRequestException(e.getStatusText(), badRequest.getErrors());
//                } catch (IOException ioException) {
//                    throw new BadRequestException(e.getStatusText(), null);
//                }
//            }
//        }
//        return returnedAccount;
//    }

    public Account getAccount(int accountId) {
        Account account = null;
        try {
            account = restTemplate.getForObject(API_BASE_URL + "accounts/" + accountId, Account.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return account;
    }

    public Account[] listAccounts() {
        Account[] accounts = null;
        try {
            accounts = restTemplate.getForObject(API_BASE_URL + "accounts", Account[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return accounts;
    }

    public double getBalance(int id) {
        Double balance = -1.0;
        try {
            balance = restTemplate.getForObject(API_BASE_URL + "accounts/" + id + "/balance", Double.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public Account getAccountByUserId(int userId) {
        Account account = null;
        String url = API_BASE_URL + "account/" + userId;
        try {
            account = restTemplate.getForObject(url , Account.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
            BasicLogger.log(e.getMessage());
        }
        return account;
    }

    public Account getUsernameByAccountId(int accountId) {
        Account account = null;
        String url = API_BASE_URL + "accounts/" + accountId;
        try {
            account = restTemplate.getForObject(url, Account.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
            BasicLogger.log(e.getMessage());
        }
        return account;
    }
}
