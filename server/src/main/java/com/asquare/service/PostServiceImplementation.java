package com.asquare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.models.Post;
import com.asquare.models.User;
import com.asquare.repository.PostRepository;

@Service
public class PostServiceImplementation implements PostService {

  @Autowired
  PostRepository postRepository;

  @Autowired
  UserService userService;

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

    return null;
  }

  @Override
  public Post findPostById(Integer postId) throws Exception {
    return null;
  }

  @Override
  public List<Post> findAllPost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAllPost'");
  }

  @Override
  public Post savedPost(Integer postId, Integer userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'savedPost'");
  }

  @Override
  public Post likedPost(Integer postId, Integer userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'likedPost'");
  }

}
