package com.asquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asquare.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
