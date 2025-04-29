package com.asquare.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.asquare.models.Comment;
import com.asquare.models.Post;
import com.asquare.models.User;
import com.asquare.repository.CommentRepository;
import com.asquare.repository.PostRepository;

public class CommentServiceImplementation implements CommentService {

  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private PostRepository postRepository;

  @Override
  public Comment createComments(Comment comment, Integer postId, Integer userId) throws Exception {
    User user = userService.findUserById(userId);
    Post post = postService.findPostById(postId);
    if (user == null && post == null) {
      throw new NoSuchElementException("User and Post not found");
    } else if (user == null) {
      throw new NoSuchElementException("User not found");
    } else if (post == null) {
      throw new NoSuchElementException("Post not found");
    }
    comment.setUser(user);
    comment.setContent(comment.getContent());
    comment.setCreatedAt(comment.getCreatedAt());
    Comment savedComment = commentRepository.save(comment);
    post.getComments().add(savedComment);
    postRepository.save(post);
    return savedComment;
  }

  @Override
  public Comment findCommentById(Integer commentId) {
    return null;
  }

  @Override
  public Comment likeComment(Integer commentId, Integer userId) {
    return null;
  }

}
