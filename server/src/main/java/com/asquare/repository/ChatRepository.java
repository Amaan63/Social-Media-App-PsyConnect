package com.asquare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asquare.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

  public List<Chat> findByUsersId(Integer userId);

}
