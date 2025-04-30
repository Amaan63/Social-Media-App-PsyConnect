package com.asquare.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {

  // The user the logged-in user wants to chat with
  private Integer targetUser;

}
