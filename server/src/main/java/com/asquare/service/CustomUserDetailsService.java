package com.asquare.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asquare.models.User;
import com.asquare.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  {
    /*
     * - UserDetailsService is an interface provided by Spring Security.
     * - It is used to fetch user-related data (like username, password, roles) from
     * a database or any source.
     * - This is very important when you are building a login system.
     * - When a user tries to log in, Spring Security will call the method
     * loadUserByUsername() of this interface.
     */
  }
  {
    /*
     * In simple words:
     * - UserDetailsService = Help Spring find user data during login
     * - loadUserByUsername = How Spring asks you:
     * "Here is a username, give me the user details."
     */}
  {
    /*
     * - loadUserByUsername method takes a username (like an email or username
     * entered during
     * login) and returns a UserDetails object containing the user's information.
     * - If the username is not found, it should throw UsernameNotFoundException.
     */}

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User Not Found with email " + username);
    }
    {
      /*
       * // GrantedAuthority is used in Spring Security to represent a permission or
       * role given to a user.
       * // Example of authorities: "ROLE_USER", "ROLE_ADMIN", "READ_PRIVILEGE", etc.
       * 
       * // When a user logs in, Spring Security checks the user's GrantedAuthority
       * // to decide what they are allowed (authorized) to do in the application.
       * 
       * // In simple words:
       * // - GrantedAuthority = What rights/permissions/roles a user has in your app.
       */}
    List<GrantedAuthority> authorities = new ArrayList<>();

    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
  }
}
