package com.asquare.service.Message;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.exceptions.ChatException;
import com.asquare.exceptions.MessageException;
import com.asquare.models.Chat;
import com.asquare.models.Message;
import com.asquare.models.User;
import com.asquare.repository.ChatRepository;
import com.asquare.repository.MessageRepository;
import com.asquare.service.Chat.ChatService;

@Service
public class MessageServiceImplementation implements MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private ChatService chatService;

  @Autowired
  private ChatRepository chatRepository;

  @Override
  public Message createMessage(User user, Integer chatId, Message reqMessage) throws MessageException, ChatException {
    if (user == null) {
      throw new MessageException("User must not be null.");
    }

    if (reqMessage == null || reqMessage.getContent() == null || reqMessage.getContent().trim().isEmpty()) {
      throw new MessageException("Message content must not be empty.");
    }

    Chat chat = chatService.findChatById(chatId);
    if (chat == null) {
      throw new MessageException("Chat not found with the given chat ID: " + chatId);
    }

    Message message = new Message();
    message.setChat(chat);
    message.setContent(reqMessage.getContent());
    message.setImage(reqMessage.getImage());
    message.setUser(user);
    message.setCreatedAt(LocalDateTime.now());

    Message savedMessage = messageRepository.save(message);

    chat.getMessagess().add(savedMessage);
    chatRepository.save(chat);

    return savedMessage;
  }

  @Override
  public List<Message> findChatsMessages(Integer chatId) throws MessageException, ChatException {
    Chat chat = chatService.findChatById(chatId);
    if (chat == null) {
      throw new MessageException("Chat not found with the given chat ID: " + chatId);
    }
    return messageRepository.findByChatId(chatId);
  }

}
