package com.blog.apiblog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blog.apiblog.dto.PostRequestDTO;
import com.blog.apiblog.dto.PostResponseDTO;
import com.blog.apiblog.dto.UserResponseDTO;
import com.blog.apiblog.exception.PostNotFoundException;
import com.blog.apiblog.model.Post;
import com.blog.apiblog.model.User;
import com.blog.apiblog.repository.PostRepository;
import com.blog.apiblog.repository.UserRespository;

@Service
public class PostService {
  
  private final PostRepository postRepository;
  private final UserRespository userRespository;

  public PostService(PostRepository postRepository, UserRespository userRespository) {
    this.postRepository = postRepository;
    this.userRespository = userRespository;
  }

  public PostResponseDTO createPost(PostRequestDTO postDTO) {
    User author = userRespository.findById(postDTO.getUserId())
        .orElseThrow(() -> new PostNotFoundException("Autor não encontrado."));

    Post newPost = new Post();
    newPost.setTitle(postDTO.getTitle());
    newPost.setContent(postDTO.getContent());
    newPost.setCreationDate(LocalDateTime.now());
    newPost.setUser(author);

    Post savedPost = postRepository.save(newPost);

    return toResponseDTO(savedPost);
  }
  
  public List<PostResponseDTO> getAllPosts() {
    List<Post> posts = postRepository.findAll();
    List<PostResponseDTO> dtos = posts.stream().map(this::toResponseDTO).collect(Collectors.toList());

    return dtos;
  }

  public PostResponseDTO getOnePost(Long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post não encontrado."));

    return toResponseDTO(post);
  }

  public PostResponseDTO toUpdatePost(Long id, PostRequestDTO newPost) {
    Post oldPost = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post não encontrado")); // Post original

    oldPost.setTitle(newPost.getTitle());
    oldPost.setContent(newPost.getContent());

    Post saved = postRepository.save(oldPost);

    return toResponseDTO(saved);
  }

  public void deletePostById(Long id) {
    Post postToDelete = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post não foi encontrado"));

    postRepository.delete(postToDelete);
  }

  private PostResponseDTO toResponseDTO(Post post) {
    PostResponseDTO dto = new PostResponseDTO();
    dto.setId(post.getId());
    dto.setTitle(post.getTitle());
    dto.setContent(post.getContent());
    dto.setCreationDate(post.getCreationDate());
    
    UserResponseDTO userDto = new UserResponseDTO();
    userDto.setId(post.getUser().getId());
    userDto.setName(post.getUser().getName());

    dto.setAuthor(userDto);

    return dto;
  }
}
