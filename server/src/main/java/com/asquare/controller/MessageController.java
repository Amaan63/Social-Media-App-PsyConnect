package com.asquare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.Message;
import com.asquare.models.User;
import com.asquare.service.Message.MessageService;
import com.asquare.service.User.UserService;

@RestController
@RequestMapping("/private")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private UserService userService;

  @PostMapping("/messages/createMessage/chat/{chatId}")
  public ResponseEntity<?> creatMessageController(
      @RequestBody Message reqMessage,
      @RequestHeader("Authorization") String jwt,
      @PathVariable("chatId") Integer chatId) {
    try {
      User user = userService.findUserByJwt(jwt);
      if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT or user not found.");
      }

      if (reqMessage == null || reqMessage.getContent() == null || reqMessage.getContent().trim().isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message content must not be empty.");
      }

      Message message = messageService.createMessage(user, chatId, reqMessage);
      return ResponseEntity.ok(message);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating message: " + ex.getMessage());
    }
  }

  @GetMapping("/messages/findChatsMessages/chat/{chatId}")
  public ResponseEntity<?> findChatsMessagesController(@PathVariable("chatId") Integer chatId) {
    try {
      List<Message> messages = messageService.findChatsMessages(chatId);
      if (messages == null || messages.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No messages found for chat ID: " + chatId);
      }
      return ResponseEntity.ok(messages);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error fetching messages: " + ex.getMessage());
    }
  }
}
