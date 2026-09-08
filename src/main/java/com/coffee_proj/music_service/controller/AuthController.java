package com.coffee_proj.music_service.controller;

import com.coffee_proj.music_service.controller.dto.UserDto;
import com.coffee_proj.music_service.exception.IncorrectPasswordException;
import com.coffee_proj.music_service.exception.UserAlreadyExistException;
import com.coffee_proj.music_service.exception.UserNotFoundException;
import com.coffee_proj.music_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto) throws UserNotFoundException, IncorrectPasswordException {
        return ResponseEntity.status(200).body(authService.login(userDto.getUsername(), userDto.getPassword()));
    }
}
