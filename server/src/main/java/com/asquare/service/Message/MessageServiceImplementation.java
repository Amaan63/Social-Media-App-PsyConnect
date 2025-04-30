package com.asquare.service.Message;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.models.Chat;
import com.asquare.models.Message;
import com.asquare.models.User;
import com.asquare.repository.MessageRepository;
import com.asquare.service.Chat.ChatService;

@Service
public class MessageServiceImplementation implements MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  ChatService chatService;

  @Override
  public Message createMessage(User user, Integer chatId, Message reqMessage) throws Exception {
    Message message = new Message();
    Chat chat = chatService.findChatById(chatId);
    message.setChat(chat);
    message.setContent(reqMessage.getContent());
    message.setImage(reqMessage.getImage());
    message.setUser(user);
    message.setCreatedAt(LocalDateTime.now());
    return messageRepository.save(message);
  }

  @Override
  public List<Message> findChatsMessages(Integer chatId) throws Exception {
    Chat chat = chatService.findChatById(chatId);
    if (chat == null) {
      throw new Exception("Chat not found with the Given Chat Id " + chatId);
    }
    return messageRepository.findByChatId(chatId);
  }

}
