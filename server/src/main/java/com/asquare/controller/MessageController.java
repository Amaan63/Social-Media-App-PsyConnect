package com.asquare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
  public Message creatMessageController(@RequestBody Message reqMessage, @RequestHeader("Authorization") String jwt,
      @PathVariable("chatId") Integer chatId) throws Exception {
    User user = userService.findUserByJwt(jwt);
    Message message = messageService.createMessage(user, chatId, reqMessage);
    return message;
  }

  @GetMapping("/messages/findChatsMessages/chat/{chatId}")
  public List<Message> findChatsMessagesController(@PathVariable("chatId") Integer chatId) throws Exception {
    return messageService.findChatsMessages(chatId);
  }
}
