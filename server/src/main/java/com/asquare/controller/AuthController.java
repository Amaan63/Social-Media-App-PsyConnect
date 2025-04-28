package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.config.JwtProvider;
import com.asquare.models.User;
import com.asquare.response.AuthResponse;
import com.asquare.service.UserService;

@RestController
@RequestMapping("/public/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  // Endpoint to create a new user
  @PostMapping("/users/signUp")
  public ResponseEntity<AuthResponse> createUser(@RequestBody User user) {
    try {
      // Check if the email already exists using the service method
      userService.checkIfUserExistsByEmail(user.getEmail());

      // If email doesn't exist, proceed to register the new user
      User savedUser = userService.registerUser(user);

      Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
          savedUser.getPassword());

      String token = JwtProvider.generatedToken(authentication);

      AuthResponse res = new AuthResponse(token, "Register Success");

      return new ResponseEntity<>(res, HttpStatus.OK); // return AuthResponse, not savedUser
    } catch (Exception e) {
      // Return a bad request response with the exception message if an error occurs
      return new ResponseEntity<>(new AuthResponse(null, e.getMessage()), HttpStatus.CONFLICT);
    }
  }
}
