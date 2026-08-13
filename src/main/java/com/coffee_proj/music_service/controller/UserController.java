package com.coffee_proj.music_service.controller;

import com.coffee_proj.music_service.entity.UserEntity;
import com.coffee_proj.music_service.exception.UserAlreadyExistExceprion;
import com.coffee_proj.music_service.repository.UserRepo;
import com.coffee_proj.music_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registation(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("клиент успешно сохранен");
        } catch (UserAlreadyExistExceprion e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("произошла ошибка");
        }
    }



    @GetMapping
    public ResponseEntity<String> getUsers() {
        try {
            return ResponseEntity.ok("сервер включилсяwhiviw jcvbihs");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("произошла ошибка");
        }
    }
}
