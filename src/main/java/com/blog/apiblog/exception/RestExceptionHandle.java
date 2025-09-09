package com.blog.apiblog.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandle {
  
  @ExceptionHandler(PostNotFoundException.class)
  public ResponseEntity<Object> handleRecursoNaoEncontradoException(PostNotFoundException ex,
      WebRequest request) {
    
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now()); // Data atual.
    body.put("status", HttpStatus.NOT_FOUND.value()); // Erro 404.
    body.put("error", ex.getMessage()); // error + a mensagem da classe de exception criada.
    body.put("path", request.getDescription(false).replace("uri=", "")); // Caminho da URL.

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }
}
