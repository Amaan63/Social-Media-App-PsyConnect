package com.asquare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.Reels;
import com.asquare.models.User;
import com.asquare.service.ReelsService;
import com.asquare.service.UserService;

@RestController
@RequestMapping("/private")
public class ReelsController {

  @Autowired
  private ReelsService reelsService;

  @Autowired
  private UserService userService;

  @PostMapping("reels/createReels")
  public ResponseEntity<Reels> createReels(
      @RequestBody Reels reel,
      @RequestHeader("Authorization") String jwt) {
    try {
      User user = userService.findUserByJwt(jwt);
      Reels createdReels = reelsService.createReel(reel, user);
      return new ResponseEntity<>(createdReels, HttpStatus.CREATED);
    } catch (Exception e) {
      // For error, still return ResponseEntity<?> to handle the String message
      return new ResponseEntity("Error creating reel: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("reels/allReels")
  public ResponseEntity<?> findAllReels() {
    try {
      List<Reels> allReels = reelsService.findAllReels();
      return new ResponseEntity<>(allReels, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error fetching all reels: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("reels/findUsersReel/users/{userId}")
  public ResponseEntity<?> findUsersReel(@PathVariable("userId") Integer userId) {
    try {
      List<Reels> allReels = reelsService.findUsersReel(userId);
      return new ResponseEntity<>(allReels, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error fetching user's reels: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }
}
