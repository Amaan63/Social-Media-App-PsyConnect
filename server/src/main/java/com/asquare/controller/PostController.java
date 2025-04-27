package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.Post;
import com.asquare.response.ApiResponse;
import com.asquare.service.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PostController {
  @Autowired
  PostService postService;

  @PostMapping("/posts/user/{userId}")
  public ResponseEntity<Post> createPost(
      @RequestBody Post post,
      @PathVariable Integer userId) throws Exception {
    Post createdPost = postService.createNewPost(post, userId);
    return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/posts/{postId}/{userId}")
  public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) {
    try {
      String message = postService.deletePost(postId, userId);
      ApiResponse res = new ApiResponse(message, true); // Agar sab sahi hai to success
      return new ResponseEntity<>(res, HttpStatus.OK);
    } catch (Exception e) {
      ApiResponse res = new ApiResponse(e.getMessage(), false); // Agar koi exception hai to fail
      return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST); // 400 error bhejo
    }
  }

  @GetMapping("/posts/{postId}")
  public ResponseEntity<Post> findPostByIdController(@PathVariable Integer postId) throws Exception {
    Post post = postService.findPostById(postId);
    return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
  }

}
