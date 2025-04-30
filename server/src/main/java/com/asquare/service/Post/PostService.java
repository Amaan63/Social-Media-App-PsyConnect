package com.asquare.service.Post;

import java.util.List;

import com.asquare.exceptions.PostException;
import com.asquare.exceptions.UserException;
import com.asquare.models.Post;

public interface PostService {

  Post createNewPost(Post post, Integer userId) throws PostException, UserException;

  String deletePost(Integer postId, Integer userId) throws PostException, UserException, Exception;

  List<Post> findPostByUserId(Integer userId) throws PostException;

  Post findPostById(Integer postId) throws PostException, Exception;

  List<Post> findAllPost() throws PostException;

  Post savedPost(Integer postId, Integer userId) throws Exception;

  Post likedPost(Integer postId, Integer userId) throws PostException, Exception;
}
