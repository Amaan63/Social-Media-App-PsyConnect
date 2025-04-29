package com.asquare.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.models.Comment;
import com.asquare.models.Post;
import com.asquare.models.User;
import com.asquare.repository.CommentRepository;
import com.asquare.repository.PostRepository;

@Service
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
  public Comment findCommentById(Integer commentId) throws Exception {
    Optional<Comment> opt = commentRepository.findById(commentId);
    if (opt.isEmpty()) {
      throw new Exception("Commeent not Exist");
    }
    return opt.get();
  }

  @Override
  public Comment likeComment(Integer commentId, Integer userId) throws Exception {
    Comment comment = findCommentById(commentId);
    User user = userService.findUserById(userId);
    if (!comment.getLiked().contains(user)) {
      comment.getLiked().add(user);
    } else {
      comment.getLiked().remove(user);
    }
    return commentRepository.save(comment);
  }

}
