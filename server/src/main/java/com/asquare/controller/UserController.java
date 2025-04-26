package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.User;
import com.asquare.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/users")
  public User createUser(@RequestBody User user) {
    User savedUser = userService.registerUser(user);
    return savedUser;
  }

  @GetMapping("/users/{userId}")
  public User getUserById(@PathVariable("userId") Integer id) throws Exception {
    return null;
  }

}
