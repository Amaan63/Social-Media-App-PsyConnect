package com.asquare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asquare.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

  @Query("select p from Post p where p.user.id=:userId")
  List<Post> findPostByUserID(Integer userId);
}
