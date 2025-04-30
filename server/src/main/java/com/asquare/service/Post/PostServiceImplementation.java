package com.asquare.service.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.asquare.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.exceptions.PostException;
import com.asquare.exceptions.UserException;
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
  public Post createNewPost(Post post, Integer userId) throws PostException, UserException {
    User user = userService.findUserById(userId);

    // Validate caption
    if (post.getCaption() == null || post.getCaption().trim().isEmpty()) {
      throw new PostException("Caption cannot be null or empty.");
    }

    boolean hasImage = post.getImage() != null && !post.getImage().trim().isEmpty();
    boolean hasVideo = post.getVideo() != null && !post.getVideo().trim().isEmpty();

    // Only one of image or video must be provided
    if ((hasImage && hasVideo) || (!hasImage && !hasVideo)) {
      throw new PostException("Provide either an image or a video, not both or none.");
    }

    Post newPost = new Post();
    newPost.setCaption(post.getCaption());
    newPost.setImage(hasImage ? post.getImage() : "Not Provided");
    newPost.setVideo(hasVideo ? post.getVideo() : "Not Provided");
    newPost.setCreatedAt(LocalDateTime.now());
    newPost.setUser(user);

    postRepository.save(newPost);
    return newPost;
  }

  @Override
  public String deletePost(Integer postId, Integer userId) throws Exception {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);

    if (post.getUser().getId() != user.getId()) {
      throw new PostException("You can't delete another users post");
    }
    // Remove this post from all users' saved posts
    List<User> allUsers = userRepository.findAll(); // You must have a method to get all users
    for (User u : allUsers) {
      if (u.getSavedPost().contains(post)) {
        u.getSavedPost().remove(post);
        userRepository.save(u); // save user after removing the saved post
      }
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
      throw new Exception("Post Not Found with id," + postId);
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
