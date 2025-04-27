package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.Post;
import com.asquare.service.PostService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PostController {
  @Autowired
  PostService postService;

  @PostMapping("/post/user/{userId}")
  public ResponseEntity<Post> createPost(
      @RequestBody Post post, @PathVariable Integer userId) throws Exception {
    Post createdPost = postService.createNewPost(post, userId);
    return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
  }
}
