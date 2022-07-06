package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.MemoryUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserDao userDao;

    public UserController(){
        this.userDao = new MemoryUserDao();
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

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public User create(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        if (userDao.create(username, password)) {

            user.setUsername(username);
            user.setPassword(password);
        }
       return user;
    }

}
