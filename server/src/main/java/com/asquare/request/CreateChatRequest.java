package com.asquare.request;

import com.asquare.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {

  // The currently logged-in user
  private User loggedInUser;

  // The user the logged-in user wants to chat with
  private User targetUser;

}
