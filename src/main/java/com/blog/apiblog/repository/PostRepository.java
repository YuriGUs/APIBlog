package com.blog.apiblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.apiblog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  
}
