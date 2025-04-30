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

import com.asquare.models.Story;
import com.asquare.models.User;
import com.asquare.service.StoryService;
import com.asquare.service.UserService;

@RestController
@RequestMapping("/private")
public class StoryController {

  @Autowired
  private StoryService storyService;

  @Autowired
  private UserService userService;

  @PostMapping("/story/createStory")
  public ResponseEntity<?> createStory(@RequestBody Story story,
      @RequestHeader("Authorization") String jwt) {
    try {
      User user = userService.findUserByJwt(jwt);
      Story createdStory = storyService.createStory(story, user);
      return ResponseEntity.ok(createdStory);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Failed to create story: " + e.getMessage());
    }
  }

  @GetMapping("/story/findUserStory/users/{userId}")
  public ResponseEntity<?> findUserStory(@PathVariable("userId") Integer userId,
      @RequestHeader("Authorization") String jwt) {
    try {
      List<Story> stories = storyService.findStoryByUserId(userId);
      return ResponseEntity.ok(stories);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Failed to retrieve stories: " + e.getMessage());
    }
  }
}
