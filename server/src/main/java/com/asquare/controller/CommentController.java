package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.Comment;
import com.asquare.models.User;
import com.asquare.service.Comment.CommentService;
import com.asquare.service.User.UserService;

@RestController
@RequestMapping("/private")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private UserService userService;

  @PostMapping("comments/createComment/post/{postId}")
  public ResponseEntity<?> createComment(
      @RequestBody Comment comment,
      @RequestHeader("Authorization") String jwt,
      @PathVariable("postId") Integer postId) {
    try {
      User user = userService.findUserByJwt(jwt);
      if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
      }

      if (comment == null || comment.getContent() == null || comment.getContent().trim().isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comment content must not be empty.");
      }

      Comment createdComment = commentService.createComments(comment, postId, user.getId());
      return ResponseEntity.ok(createdComment);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Failed to create comment: " + ex.getMessage());
    }
  }

  @PutMapping("comments/likeComment/{commentId}")
  public ResponseEntity<?> likeComment(
      @RequestHeader("Authorization") String jwt,
      @PathVariable("commentId") Integer commentId) {
    try {
      User user = userService.findUserByJwt(jwt);
      if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
      }

      Comment likedComment = commentService.likeComment(commentId, user.getId());
      return ResponseEntity.ok(likedComment);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to like comment: " + ex.getMessage());
    }
  }

}
