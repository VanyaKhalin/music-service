package com.coffee_proj.music_service.controller;

import com.coffee_proj.music_service.entity.UserEntity;
import com.coffee_proj.music_service.exception.UserAlreadyExistException;
import com.coffee_proj.music_service.exception.UserNotFoundException;
import com.coffee_proj.music_service.repository.UserRepo;
import com.coffee_proj.music_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) throws UserAlreadyExistException {
        userService.registration(user);
        return ResponseEntity.status(201).body("клиент успешно сохранен");
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.status(204).body(userService.delete(id));
    }
}
