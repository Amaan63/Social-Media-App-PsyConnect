package com.asquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asquare.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
