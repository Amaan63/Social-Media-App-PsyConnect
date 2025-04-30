package com.asquare.service.Message;

import java.util.List;

import com.asquare.models.Message;
import com.asquare.models.User;

public interface MessageService {

  public Message createMessage(User user, Integer chatId, Message message) throws Exception;

  public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
