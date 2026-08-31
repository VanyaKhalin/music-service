package com.coffee_proj.music_service.service;

import com.coffee_proj.music_service.entity.UserEntity;
import com.coffee_proj.music_service.exception.UserAlreadyExistException;
import com.coffee_proj.music_service.exception.UserNotFoundException;
import com.coffee_proj.music_service.model.User;
import com.coffee_proj.music_service.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        Optional<UserEntity> user = userRepo.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("Пользователь с таким id не найден");
        }
        return User.fromEntyity(user.get());
    }

    public Long delete(Long id) throws UserNotFoundException {
        Optional<UserEntity> userOpt = userRepo.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("Пользователь с таким Id не найден");
        }
        userRepo.deleteById(id);
        return id;
    }
}
