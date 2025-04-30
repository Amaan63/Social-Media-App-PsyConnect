package com.asquare.service.User;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.asquare.exceptions.UserException;
import com.asquare.models.User;

public interface UserService {

  public User registerUser(User user);

  public User findUserById(Integer userId) throws UserException;

  public User findUserByEmail(String email);

  public User followUser(Integer userId1, Integer userId2) throws UserException;

  public User updateuser(User user, Integer userId) throws UserException;

  public List<User> searchuser(String query);

  public void checkIfUserExistsByEmail(String email) throws UserException;

  public Authentication authenticate(String email, String password) throws UserException;

  public User findUserByJwt(String jwt) throws UserException;
}
