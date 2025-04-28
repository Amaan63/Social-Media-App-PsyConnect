package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.User;
import com.asquare.service.UserService;

@RestController
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/public/users")
  public User createUser(@RequestBody User user) {
    User savedUser = userService.registerUser(user);
    return savedUser;
  }
}
