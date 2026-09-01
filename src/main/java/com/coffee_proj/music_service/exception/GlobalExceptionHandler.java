package com.coffee_proj.music_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity handleUserAlreadyExist(UserAlreadyExistException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Произошла ошибка");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MusicNotFoundException.class)
    public ResponseEntity handleMusicNotFoundException(MusicNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(PasswordIsTooShortException.class)
    public ResponseEntity handlePasswordIsTooShortException(PasswordIsTooShortException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
