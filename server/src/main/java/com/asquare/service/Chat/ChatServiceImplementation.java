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
  public Chat createChat(User loggedInUser, User targetUser) throws Exception {
    Chat isExist = chatRepository.findChatBetweenUsers(loggedInUser, targetUser);
    if (isExist != null) {
      return isExist;
    }
    Chat chat = new Chat();
    chat.getUsers().add(targetUser);
    chat.getUsers().add(loggedInUser);
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

  @Override
  public List<Chat> findUsersChat(Integer userId) throws Exception {
    return chatRepository.findByUsersId(userId);
  }
}
