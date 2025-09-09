package com.blog.apiblog.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {
  
  private Long id;
  private String title;
  private String content;
  private LocalDateTime creationDate;

  private UserResponseDTO author;
}
