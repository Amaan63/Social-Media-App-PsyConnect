package com.asquare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.Post;
import com.asquare.models.User;
import com.asquare.response.ApiResponse;
import com.asquare.service.Post.PostService;
import com.asquare.service.User.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/private")
public class PostController {

  @Autowired
  PostService postService;

  @Autowired
  UserService userService;

  @PostMapping("/posts/createPost")
  public ResponseEntity<Post> createPost(
      @RequestBody Post post,
      @RequestHeader("Authorization") String jwt) throws Exception {
    User user = userService.findUserByJwt(jwt);
    Post createdPost = postService.createNewPost(post, user.getId());
    return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/posts/deletePost/{postId}")
  public ResponseEntity<ApiResponse> deletePost(
      @PathVariable Integer postId,
      @RequestHeader("Authorization") String jwt) {
    try {
      User user = userService.findUserByJwt(jwt);
      String message = postService.deletePost(postId, user.getId());
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

  @GetMapping("/posts/user/{userId}")
  public ResponseEntity<List<Post>> findUsersPostController(@PathVariable Integer userId) {
    List<Post> posts = postService.findPostByUserId(userId);
    return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
  }

  @GetMapping("/posts/allPosts")
  public ResponseEntity<List<Post>> findAllPostController() {
    List<Post> posts = postService.findAllPost();
    return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
  }

  @PutMapping("/posts/savePost/{postId}")
  public ResponseEntity<Post> savePostController(
      @PathVariable Integer postId,
      @RequestHeader("Authorization") String jwt) throws Exception {
    User user = userService.findUserByJwt(jwt);
    Post post = postService.savedPost(postId, user.getId());
    return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
  }

  @PutMapping("/posts/likePost/{postId}")
  public ResponseEntity<Post> likePostController(
      @PathVariable Integer postId,
      @RequestHeader("Authorization") String jwt) throws Exception {
    User user = userService.findUserByJwt(jwt);
    Post post = postService.likedPost(postId, user.getId());
    return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
  }
}
