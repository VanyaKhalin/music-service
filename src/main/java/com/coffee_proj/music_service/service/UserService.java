package com.coffee_proj.music_service.service;

import com.coffee_proj.music_service.entity.UserEntity;
import com.coffee_proj.music_service.exception.UserAlreadyExistExceprion;
import com.coffee_proj.music_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistExceprion {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistExceprion("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }
}
