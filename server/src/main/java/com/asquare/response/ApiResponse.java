package com.asquare.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
  // Ye field response ka message store karegi, jaise "Post deleted successfully"
  private String message;

  // Ye field batayegi ki operation success hua ya fail (true/false)
  private boolean status;
}
