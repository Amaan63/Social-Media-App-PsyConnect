package com.asquare.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String gender;
  private List<Integer> followers;
  private List<Integer> followings;
}
