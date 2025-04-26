package com.asquare.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

  @Id
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
}
