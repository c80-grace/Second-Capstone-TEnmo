package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MemoryUserDao implements UserDao {

    public List<User> users = new ArrayList<>();


    public MemoryUserDao() {
        if (users.size() == 0) {

        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public int findIdByUsername(String username) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user.getId();
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public boolean create(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        for (User user : users){
            if (newUser.getUsername().equalsIgnoreCase(user.getUsername()))
                System.out.println("Sorry, that username already exists");
            return false;
        }
        users.add(newUser);
        System.out.println("User created");
        return true;
    }
}
