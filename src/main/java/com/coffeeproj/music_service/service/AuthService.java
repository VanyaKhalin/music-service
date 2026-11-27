package com.coffeeproj.music_service.service;

import com.coffeeproj.music_service.entity.UserEntity;
import com.coffeeproj.music_service.exception.IncorrectPasswordException;
import com.coffeeproj.music_service.exception.UserNotFoundException;
import com.coffeeproj.music_service.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(String username, String password) throws UserNotFoundException, IncorrectPasswordException {
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("Неверный пароль");
        }

        return jwtService.generateToken(user.getUsername(), user.getId());
    }
}
