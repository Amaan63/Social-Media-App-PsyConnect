package com.asquare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.User;
import com.asquare.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/private/user/{userId}")
  public User getUserById(@PathVariable("userId") Integer userId) throws Exception {
    User user = userService.findUserById(userId);
    return user;
  }

  @PutMapping("/private/user/{userId}")
  public User updateUser(@RequestBody User user, @PathVariable("userId") Integer userId) throws Exception {
    User updatedUser = userService.updateuser(user, userId);
    return updatedUser;
  }

  @PutMapping("/private/users/follow/{userId1}/{userId2}")
  public User followUserHandler(
      @PathVariable("userId1") Integer userId1,
      @PathVariable("userId2") Integer userId2) throws Exception {
    User user = userService.followUser(userId1, userId2);
    return user;
  }

  @GetMapping("/private/users/search")
  public List<User> searchUser(@RequestParam("query") String query) {
    List<User> users = userService.searchuser(query);
    return users;
  }

}
