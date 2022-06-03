package com.foodware.auth.controller;

import com.foodware.auth.entity.AuthToken;
import com.foodware.auth.entity.UserRequest;
import com.foodware.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(path = "register")
  public ResponseEntity<String> registerUser(@RequestBody UserRequest userRequest) {
    return userService.registerUser(userRequest.getPhoneNumber(), userRequest.getPassword());
  }

  @PostMapping(path = "login")
  public AuthToken loginUser(@RequestBody UserRequest userRequest) {
    return userService.login(userRequest.getPhoneNumber(), userRequest.getPassword());
  }
}
