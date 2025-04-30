package com.asquare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.User;
import com.asquare.service.User.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController()
@RequestMapping("/private")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/user/{userId}")
  public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {
    try {
      User user = userService.findUserById(userId);
      if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
      }
      return ResponseEntity.ok(user);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch user: " + ex.getMessage());
    }
  }

  @PutMapping("/user/update")
  public ResponseEntity<?> updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) {
    try {
      User reqUser = userService.findUserByJwt(jwt);
      if (reqUser == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
      }
      User updatedUser = userService.updateuser(user, reqUser.getId());
      return ResponseEntity.ok(updatedUser);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user: " + ex.getMessage());
    }
  }

  @PutMapping("/users/follow/{userId2}")
  public ResponseEntity<?> followUserHandler(
      @RequestHeader("Authorization") String jwt,
      @PathVariable("userId2") Integer userId2) {
    try {
      User reqUser = userService.findUserByJwt(jwt);
      if (reqUser == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
      }
      User user = userService.followUser(reqUser.getId(), userId2);
      return ResponseEntity.ok(user);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to follow user: " + ex.getMessage());
    }
  }

  @GetMapping("/users/search")
  public ResponseEntity<?> searchUser(@RequestParam("query") String query) {
    try {
      List<User> users = userService.searchuser(query);
      if (users == null || users.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found matching the query: " + query);
      }
      return ResponseEntity.ok(users);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Search failed: " + ex.getMessage());
    }
  }

  @GetMapping("/user/profile")
  public ResponseEntity<?> getUserFromToken(@RequestHeader("Authorization") String jwt) {
    try {
      User user = userService.findUserByJwt(jwt);
      if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
      }
      user.setPassword(null);
      return ResponseEntity.ok(user);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Failed to fetch profile: " + ex.getMessage());
    }
  }

}
