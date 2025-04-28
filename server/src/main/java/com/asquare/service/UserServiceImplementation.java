package com.asquare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asquare.models.User;
import com.asquare.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(User user) {
    User newUser = new User();
    newUser.setEmail(user.getEmail());
    newUser.setFirstName(user.getFirstName());
    newUser.setLastName(user.getLastName());
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    newUser.setGender(user.getGender());
    User savedUser = userRepository.save(newUser);
    return savedUser;
  }

  @Override
  public User findUserById(Integer userId) throws Exception {
    Optional<User> user = userRepository.findById(userId);
    if (user.isPresent()) {
      return user.get();
    }
    throw new Exception("user Not Exixt with the UserId");
  }

  @Override
  public User findUserByEmail(String email) {
    User user = userRepository.findByEmail(email);
    return user;
  }

  @Override
  public User followUser(Integer userId1, Integer userId2) throws Exception {
    // Here user1 wants to follow user2
    User user1 = findUserById(userId1);
    User user2 = findUserById(userId2);
    // Here in user2 follower user1 is added
    user2.getFollowers().add(user1.getId());
    // Here in user1 following user2 is added
    user1.getFollowings().add(user2.getId());
    // saving the users
    userRepository.save(user1);
    userRepository.save(user2);
    // user1 wants to follow user2 so we will return user1
    return user1;
  }

  @Override
  public User updateuser(User user, Integer userId) throws Exception {
    Optional<User> user1 = userRepository.findById(userId);
    if (user1.isEmpty()) {
      throw new Exception("User not exixts with the id" + userId);
    }
    User oldUser = user1.get();
    if (user.getFirstName() != null) {
      oldUser.setFirstName(user.getFirstName());
    }
    if (user.getLastName() != null) {
      oldUser.setLastName(user.getLastName());
    }
    if (user.getEmail() != null) {
      oldUser.setEmail(user.getEmail());
    }
    if (user.getGender() != null) {
      oldUser.setGender(user.getGender());
    }
    User updateduser = userRepository.save(oldUser);
    return updateduser;
  }

  @Override
  public List<User> searchuser(String query) {
    return userRepository.searchUser(query);
  }

  @Override
  public void checkIfUserExistsByEmail(String email) throws Exception {
    // Check if a user already exists with the given email
    User isExist = userRepository.findByEmail(email);

    // If the email is already used, throw an exception
    if (isExist != null) {
      throw new Exception("This email is already used with another account");
    }
  }

}
