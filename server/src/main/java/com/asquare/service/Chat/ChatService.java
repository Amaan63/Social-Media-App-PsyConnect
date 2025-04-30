package com.asquare.service.Chat;

import java.util.List;

import com.asquare.models.Chat;
import com.asquare.models.User;

public interface ChatService {

  // Req user is logged in and user2 is with the reqUser chat with
  public Chat createChat(User reqUser, User user2) throws Exception;

  public Chat findChatById(Integer chatId) throws Exception;

  public List<Chat> findUsersChat(Integer userId) throws Exception;

}
