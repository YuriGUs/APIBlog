package com.blog.apiblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
  
  private String title;
  private String content;
  private Long userId; // Autor
}