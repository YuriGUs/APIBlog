package com.blog.apiblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.apiblog.model.User;

public interface UserRespository extends JpaRepository<User, Long>{
  
}
