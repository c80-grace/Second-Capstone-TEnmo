package com.techelevator.tenmo.services;

import com.techelevator.tenmo.Exceptions.BadRequestException;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class UserService {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

    public User[] listUsers(){
        User[] users = null;
        try {
            users = restTemplate.getForObject(API_BASE_URL + "users", User[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;
    }

    public User getUserById(int id){
        User user = null;
        try {
            user = restTemplate.getForObject(API_BASE_URL + "user/" + id, User.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

    public User findByUsername(String username){
        User user = null;
        try {
            user = restTemplate.getForObject(API_BASE_URL + "users/" + username, User.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

    public int findIdByUsername(String username) {
        Integer id = -1;
        try {
            id = restTemplate.getForObject(API_BASE_URL + username + "/users", Integer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
            return id;
    }

}


