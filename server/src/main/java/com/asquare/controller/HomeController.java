package com.asquare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/public/home")
  public String homeControllerHandler() {
    return "this is home controller";
  }
}
