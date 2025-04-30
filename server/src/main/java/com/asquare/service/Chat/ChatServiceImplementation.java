package com.asquare.service.Chat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asquare.exceptions.ChatException;
import com.asquare.models.Chat;
import com.asquare.models.User;
import com.asquare.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {

  @Autowired
  private ChatRepository chatRepository;

  @Override
  public Chat createChat(User loggedInUser, User targetUser) throws ChatException {
    if (loggedInUser == null || targetUser == null) {
      throw new ChatException("Both users must be valid to create a chat.");
    }

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
  public Chat findChatById(Integer chatId) throws ChatException {
    return chatRepository.findById(chatId)
        .orElseThrow(() -> new ChatException("Chat not found with id - " + chatId));
  }

  @Override
  public List<Chat> findUsersChat(Integer userId) throws ChatException {
    List<Chat> chats = chatRepository.findByUsersId(userId);
    if (chats.isEmpty()) {
      throw new ChatException("No chats found for user with id - " + userId);
    }
    return chats;
  }
}
