package com.asquare.service.User;

import java.util.List;
import java.util.Optional;

import com.asquare.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asquare.config.JwtProvider;
import com.asquare.models.User;
import com.asquare.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  CustomUserDetailsService customUserDetailsService;

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
    throw new Exception("user Not Exist with the UserId " + userId);
  }

  @Override
  public User findUserByEmail(String email) {
    User user = userRepository.findByEmail(email);
    return user;
  }

  @Override
  public User followUser(Integer reqUserId, Integer userId2) throws Exception {
    // Here request user wants to follow user2
    User reqUser = findUserById(reqUserId);
    User user2 = findUserById(userId2);
    // Here in user2 follower reqUser is added
    user2.getFollowers().add(reqUser.getId());
    // Here in reqUser following user2 is added
    reqUser.getFollowings().add(user2.getId());
    // saving the users
    userRepository.save(reqUser);
    userRepository.save(user2);
    // reqUser wants to follow user2 so we will return reqUser
    return reqUser;
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

  @Override
  public Authentication authenticate(String email, String password) throws Exception {
    if (email == null || email.trim().isEmpty()) {
      throw new BadCredentialsException("Email is required, Cannot Be Null");
    }
    try {
      // Load user details from the database based on the provided email
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

      // If the provided password does not match the stored encoded password, throw an
      // exception
      if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        throw new BadCredentialsException("Password didn't match");
      }

      // If both email and password are correct, create and return an Authentication
      // object
      // This Authentication object contains the user details and their authorities
      // (roles/permissions)
      return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    } catch (UsernameNotFoundException ex) {
      // If user not found by email
      // If no user is found with the given email, throw an exception indicating
      // invalid username
      throw new BadCredentialsException("The email " + email + " is not registered");
    }
  }

  @Override
  public User findUserByJwt(String jwt) throws Exception {
    try {
      String email = JwtProvider.getEmailFromJwtToken(jwt);

      if (email == null || email.isEmpty()) {
        throw new Exception("Invalid JWT token. Email not found.");
      }

      User user = userRepository.findByEmail(email);
      if (user == null) {
        throw new Exception("User not found for email: " + email);
      }

      return user;
    } catch (Exception e) {
      // Optional: log the error for debugging
      throw new Exception("Error while extracting user from JWT: " + e.getMessage());
    }
  }

}
