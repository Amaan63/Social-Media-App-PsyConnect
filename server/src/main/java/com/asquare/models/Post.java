package com.asquare.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String caption;

  private String image;

  private String video;

  @ManyToOne
  private User user;

  // Change @OneToMany to @ManyToMany
  @ManyToMany
  private List<User> liked = new ArrayList<>();

  private LocalDateTime createdAt;

  @OneToMany
  private List<Comment> comments = new ArrayList<>();
}
