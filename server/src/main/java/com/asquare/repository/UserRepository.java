package com.asquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asquare.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  public User findByEmail(String email);
}
