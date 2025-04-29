package com.asquare.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.asquare.models.User;

public interface UserService {

  public User registerUser(User user);

  public User findUserById(Integer userId) throws Exception;

  public User findUserByEmail(String email);

  public User followUser(Integer userId1, Integer userId2) throws Exception;

  public User updateuser(User user, Integer userId) throws Exception;

  public List<User> searchuser(String query);

  public void checkIfUserExistsByEmail(String email) throws Exception;

  public Authentication authenticate(String email, String password) throws Exception;

  public User findUserByJwt(String jwt) throws Exception;
}
