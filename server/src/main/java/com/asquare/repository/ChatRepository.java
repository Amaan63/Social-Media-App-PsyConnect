package com.asquare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asquare.models.Chat;
import com.asquare.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

  public List<Chat> findByUsersId(Integer userId);

  // Custom query to find a Chat that includes both users:
  // Custom query to find a Chat that includes both the logged-in user and the
  // target user
  @Query("select c from Chat c where :loggedInUser member of c.users and :targetUser member of c.users")
  public Chat findChatBetweenUsers(@Param("loggedInUser") User loggedInUser, @Param("targetUser") User targetUser);

}
