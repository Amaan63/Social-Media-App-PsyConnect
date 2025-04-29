package com.asquare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.Comment;
import com.asquare.models.User;
import com.asquare.service.CommentService;
import com.asquare.service.UserService;

@RestController
@RequestMapping("/private")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private UserService userService;

  @PostMapping("comments/createComment/post/{postId}")
  public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,
      @PathVariable("postId") Integer postId) throws Exception {
    User user = userService.findUserByJwt(jwt);
    Comment createdComment = commentService.createComments(comment, postId, user.getId());
    return createdComment;
  }
}
