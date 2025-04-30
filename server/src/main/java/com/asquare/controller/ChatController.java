package com.asquare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asquare.models.Chat;
import com.asquare.models.User;
import com.asquare.request.CreateChatRequest;
import com.asquare.service.Chat.ChatService;
import com.asquare.service.User.UserService;

@RestController
@RequestMapping("/private")
public class ChatController {

  @Autowired
  private ChatService chatService;

  @Autowired
  private UserService userService;

  @PostMapping("/chats/createChat")
  public ResponseEntity<?> createChatController(@RequestBody CreateChatRequest req,
      @RequestHeader("Authorization") String jwt) {

    try {
      User loggedInUser = userService.findUserByJwt(jwt);
      if (loggedInUser == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
      }

      User targetUser = userService.findUserById(req.getTargetUser());
      if (targetUser == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Target user not found.");
      }

      Chat chat = chatService.createChat(loggedInUser, targetUser);
      return ResponseEntity.ok(chat);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
  }
}
