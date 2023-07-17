package com.codewithrizwan.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrizwan.blog.entities.Comment;

public interface CommentRepo  extends JpaRepository<Comment	, Integer> {

}