package com.asquare.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptions {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ex, WebRequest request) {
    ErrorDetails error = new ErrorDetails(
        ex.getMessage(),
        request.getDescription(false),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ex, WebRequest request) {
    ErrorDetails error = new ErrorDetails(
        ex.getMessage(),
        request.getDescription(false),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ChatException.class)
  public ResponseEntity<ErrorDetails> chatExceptionHandler(ChatException ex, WebRequest request) {
    ErrorDetails error = new ErrorDetails(
        ex.getMessage(),
        request.getDescription(false),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MessageException.class)
  public ResponseEntity<ErrorDetails> messageExceptionHandler(MessageException ex, WebRequest request) {
    ErrorDetails error = new ErrorDetails(
        ex.getMessage(),
        request.getDescription(false),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

}
