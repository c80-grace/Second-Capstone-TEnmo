package com.techelevator.tenmo.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.tenmo.Exceptions.BadRequest;
import com.techelevator.tenmo.Exceptions.BadRequestException;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.io.IOException;


public class TransferService {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

    public Transfer[] listTransfers() {
        Transfer[] transfers = null;
        try {
            transfers = restTemplate.getForObject(API_BASE_URL + "transfers", Transfer[].class);

        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }

    public Transfer findTransferById(int transferId){
        Transfer transfer = null;
        try {
            transfer = restTemplate.getForObject(API_BASE_URL + "tranfers/" + transferId, Transfer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfer;
    }

    public Transfer findUsernameByAccountId(int accountId){
        Transfer transfer = null;
        try {
            transfer = restTemplate.getForObject(API_BASE_URL + accountId +"/transfers", Transfer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfer;
    }


    public Transfer transferFrom(Transfer newTransfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(newTransfer, headers);

        Transfer returnedTransfer = null;
        try {
           returnedTransfer = restTemplate.exchange(API_BASE_URL + "transfers/" + newTransfer.getAccountFrom() + "/" + newTransfer.getAccountTo() + "/" + newTransfer.getAmount(), HttpMethod.PUT, entity, Transfer.class).getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            //System.out.println(e.getMessage()); THE PROBLEM CAUSER!!!!!!!!!!!
            BasicLogger.log(e.getMessage());
        }

        return returnedTransfer;
    }


}
