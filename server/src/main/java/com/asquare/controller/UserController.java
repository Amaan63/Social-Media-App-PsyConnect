package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.User;
import com.asquare.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/users")
  public User createUser(@RequestBody User user) {
    User savedUser = userService.registerUser(user);
    return savedUser;
  }

}
