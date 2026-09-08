package com.coffeeproj.music_service.service;

import com.coffeeproj.music_service.controller.dto.UserDto;
import com.coffeeproj.music_service.entity.UserEntity;
import com.coffeeproj.music_service.exception.PasswordIsTooShortException;
import com.coffeeproj.music_service.exception.UserAlreadyExistException;
import com.coffeeproj.music_service.exception.UserNotFoundException;
import com.coffeeproj.music_service.model.User;
import com.coffeeproj.music_service.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registration(UserDto userDto) throws UserAlreadyExistException {
        if (userRepo.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        UserEntity user = new UserEntity(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole());
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

    public User updateUsername(Long id, UserDto userDto) throws UserNotFoundException, UserAlreadyExistException, PasswordIsTooShortException {
        Optional<UserEntity> userOpt = userRepo.findById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        UserEntity user = userOpt.get();

        if (userDto.getUsername() != null) {
            UserEntity otherUser = userRepo.findByUsername(userDto.getUsername());
            if (otherUser != null && !otherUser.getId().equals(id)) {
                throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
            }
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null) {
            if (userDto.getPassword().length() < 4) {
                throw new PasswordIsTooShortException("Слишком короткий пароль: минимум 4 символа");
            }
            user.setPassword(userDto.getPassword());
        }
        if (userDto.getRole() != null) {
            user.setRole(userDto.getRole());
        }
        return User.fromEntyity(userRepo.save(user));
    }
}
