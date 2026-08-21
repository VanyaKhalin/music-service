package com.coffee_proj.music_service.service;

import com.coffee_proj.music_service.entity.UserEntity;
import com.coffee_proj.music_service.exception.UserAlreadyExistExceprion;
import com.coffee_proj.music_service.exception.UserNotFoundException;
import com.coffee_proj.music_service.model.User;
import com.coffee_proj.music_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public UserEntity registration(UserEntity user) throws UserAlreadyExistExceprion {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistExceprion("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        Optional<UserEntity> user = userRepo.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user.get());
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}
