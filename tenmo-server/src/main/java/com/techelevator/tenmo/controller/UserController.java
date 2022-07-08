package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {
    private UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public User get(@PathVariable int id) {
        return userDao.getUserById(id);
    }

    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public User getByUsername(@PathVariable String username) {
        return userDao.findByUsername(username);
    }

    @RequestMapping(path = "/{username}/users", method = RequestMethod.GET)
    public int getIdByUsername(@PathVariable String username) {
        return userDao.findIdByUsername(username);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findAll();
    }



}
