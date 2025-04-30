package com.asquare.service.Message;

import java.util.List;

import com.asquare.exceptions.ChatException;
import com.asquare.exceptions.MessageException;
import com.asquare.models.Message;
import com.asquare.models.User;

public interface MessageService {

  public Message createMessage(User user, Integer chatId, Message message) throws MessageException, ChatException;

  public List<Message> findChatsMessages(Integer chatId) throws MessageException, ChatException;

}
