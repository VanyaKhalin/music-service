package com.coffeeproj.music_service.controller;

import com.coffeeproj.music_service.controller.dto.AuthUserDto;
import com.coffeeproj.music_service.controller.dto.UserDto;
import com.coffeeproj.music_service.exception.PasswordIsTooShortException;
import com.coffeeproj.music_service.exception.UserAlreadyExistException;
import com.coffeeproj.music_service.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coffeeproj.music_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registration(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        userService.registration(userDto);
        return ResponseEntity.status(201).body("клиент успешно сохранен");
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestAttribute("authUserDto") AuthUserDto authUserDto) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getOne(authUserDto.getId()));
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestAttribute("authUserDto") AuthUserDto authUserDto) throws UserNotFoundException {
        return ResponseEntity.status(204).body(userService.delete(authUserDto.getId()));
    }

    @PatchMapping()
    public ResponseEntity updateUser(@RequestAttribute("authUserDto") AuthUserDto authUserDto, @RequestBody UserDto userDto) throws UserNotFoundException, UserAlreadyExistException, PasswordIsTooShortException {
        return ResponseEntity.status(200).body(userService.updateUsername(authUserDto.getId(), userDto));
    }

}
