package com.foodware.auth.controller;

import com.foodware.auth.entity.User;
import com.foodware.auth.exception.InvalidCredentialsException;
import com.foodware.auth.exception.UserAlreadyExistException;
import com.foodware.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "register")
    public String registerUser(@RequestBody User user) {
        try {
            return userService.register(user);
        } catch (UserAlreadyExistException e) {
            return "Could not register user " + e.getMessage();
        }
    }

    @PostMapping(path = "login")
    public String loginUser(@RequestBody User user) {
        try {
            return userService.login(user);
        } catch (InvalidCredentialsException e) {
            return "Invalid credentials " + e.getMessage();
        }
    }
}
