package com.asquare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.models.User;
import com.asquare.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public User registerUser(User user) {
    User newUser = new User();
    newUser.setEmail(user.getEmail());
    newUser.setFirstName(user.getFirstName());
    newUser.setLastName(user.getLastName());
    newUser.setPassword(user.getPassword());
    newUser.setId(user.getId());
    User savedUser = userRepository.save(newUser);
    return savedUser;
  }

  @Override
  public User findUserById(Integer userId) {
    return null;
  }

  @Override
  public User findUserByEmail(String email) {
    return null;
  }

  @Override
  public User followUser(Integer userId1, Integer userId2) {
    return null;
  }

  @Override
  public User updateuser(User user) {
    return null;
  }

  @Override
  public List<User> searchuser(String query) {
    return null;
  }

}
