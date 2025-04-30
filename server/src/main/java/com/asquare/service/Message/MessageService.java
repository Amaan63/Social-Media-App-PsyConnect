package com.asquare.service.Message;

import java.util.List;

import com.asquare.models.Chat;
import com.asquare.models.Message;

public interface MessageService {

  public Message createMessage(Integer userId, Integer chatId, Chat chat) throws Exception;

  public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
