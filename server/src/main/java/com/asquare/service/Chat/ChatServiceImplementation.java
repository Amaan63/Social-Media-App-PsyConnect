package com.asquare.service.Chat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.models.Chat;
import com.asquare.models.User;
import com.asquare.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {

  @Autowired
  private ChatRepository chatRepository;

  @Override
  public Chat createChat(User reqUser, User user2) throws Exception {
    Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);
    if (isExist != null) {
      return isExist;
    }
    Chat chat = new Chat();
    chat.getUsers().add(user2);
    chat.getUsers().add(reqUser);
    chat.setCreatedAt(LocalDateTime.now());
    return chatRepository.save(chat);
  }

  @Override
  public Chat findChatById(Integer chatId) throws Exception {
    Optional<Chat> opt = chatRepository.findById(chatId);
    if (opt.isEmpty()) {
      throw new Exception("Chat Not Found with id - " + chatId);
    }
    return opt.get();
  }
}
