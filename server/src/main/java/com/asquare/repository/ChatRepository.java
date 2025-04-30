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
  // - :user must be a member of c.users
  // - :reqUser must also be a member of c.users
  @Query("select c from Chat c where :user Member of c.users And :reqUser Member of c.users")
  public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);

}
