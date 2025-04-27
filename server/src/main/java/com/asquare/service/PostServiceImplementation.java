package com.asquare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.models.Post;
import com.asquare.models.User;
import com.asquare.repository.PostRepository;
import com.asquare.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {

  @Autowired
  PostRepository postRepository;

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @Override
  public Post createNewPost(Post post, Integer userId) throws Exception {

    User user = userService.findUserById(userId);

    Post newPost = new Post();
    newPost.setCaption(post.getCaption());
    newPost.setImage(post.getImage());
    // newPost.setCreatedAt(new LocalDateTime);
    newPost.setVideo(post.getVideo());
    newPost.setUser(user);
    postRepository.save(newPost);
    return newPost;
  }

  @Override
  public String deletePost(Integer postId, Integer userId) throws Exception {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);

    if (post.getUser().getId() != user.getId()) {
      throw new Exception("You can't delete another users post");
    }
    postRepository.delete(post);
    return "Post Deleted Successfully";

  }

  @Override
  public List<Post> findPostByUserId(Integer userId) {
    return postRepository.findPostByUserID(userId);
  }

  @Override
  public Post findPostById(Integer postId) throws Exception {
    Optional<Post> opt = postRepository.findById(postId);
    if (opt.isEmpty()) {
      throw new Exception("Post Not Found with id" + postId);
    }
    return opt.get();
  }

  @Override
  public List<Post> findAllPost() {
    return postRepository.findAll();
  }

  @Override
  public Post savedPost(Integer postId, Integer userId) throws Exception {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);

    if (user.getSavedPost().contains(post)) {
      user.getSavedPost().remove(post);
    } else {
      user.getSavedPost().add(post);
    }
    userRepository.save(user);
    return post;
  }

  @Override
  public Post likedPost(Integer postId, Integer userId) throws Exception {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);
    if (post.getLiked().contains(user)) {
      post.getLiked().remove(user);
    } else {
      post.getLiked().add(user);
    }
    return postRepository.save(post);
  }

}
