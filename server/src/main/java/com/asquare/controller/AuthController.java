package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.User;
import com.asquare.service.UserService;

@RestController
public class AuthController {

  @Autowired
  private UserService userService;

  // Endpoint to create a new user
  @PostMapping("/public/users/signUp")
  public ResponseEntity<Object> createUser(@RequestBody User user) {
    try {
      // Check if the email already exists using the service method
      userService.checkIfUserExistsByEmail(user.getEmail());

      // If email doesn't exist, proceed to register the new user
      User newUser = userService.registerUser(user);

      // Return the newly created user
      return new ResponseEntity<>(newUser, HttpStatus.OK);
    } catch (Exception e) {
      // Return a bad request response with the exception message if an error occurs
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }
}
