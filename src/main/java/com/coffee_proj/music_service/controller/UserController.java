package com.coffee_proj.music_service.controller;

import com.coffee_proj.music_service.controller.dto.UserDto;
import com.coffee_proj.music_service.exception.PasswordIsTooShortException;
import com.coffee_proj.music_service.exception.UserAlreadyExistException;
import com.coffee_proj.music_service.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coffee_proj.music_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        userService.registration(userDto);
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

    @PatchMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws UserNotFoundException, UserAlreadyExistException, PasswordIsTooShortException {
        return ResponseEntity.status(200).body(userService.updateUsername(id, userDto));
    }

}
