package com.asquare.service;

import java.util.List;

import com.asquare.models.User;

public interface UserService {

  public User registerUser(User user);

  public User findUserById(Integer userId) throws Exception;

  public User findUserByEmail(String email);

  public User followUser(Integer userId1, Integer userId2) throws Exception;

  public User updateuser(User user);

  public List<User> searchuser(String query);
}
