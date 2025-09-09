package com.blog.apiblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apiblog.dto.PostRequestDTO;
import com.blog.apiblog.dto.PostResponseDTO;
import com.blog.apiblog.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
  
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping
  public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO) {
    PostResponseDTO newPost = postService.createPost(postRequestDTO);
    return new ResponseEntity<>(newPost, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
    List<PostResponseDTO> listOfPost = postService.getAllPosts();

    return ResponseEntity.ok(listOfPost);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostResponseDTO> getOnePost(@PathVariable Long id) {
    PostResponseDTO foundPost = postService.getOnePost(id);
    return ResponseEntity.ok(foundPost);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostResponseDTO> toUpdatePost(@PathVariable Long id, PostRequestDTO newPost) {
    PostResponseDTO update = postService.toUpdatePost(id, newPost);
    return ResponseEntity.ok(update);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
    postService.deletePostById(id);
    return ResponseEntity.noContent().build();
  }
}
