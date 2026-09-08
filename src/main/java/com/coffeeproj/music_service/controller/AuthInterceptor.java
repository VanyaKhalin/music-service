package com.coffeeproj.music_service.controller;

import com.coffeeproj.music_service.controller.dto.AuthUserDto;
import com.coffeeproj.music_service.service.JWTService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final JWTService jwtService;

    public AuthInterceptor(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String token = authHeader.substring(authHeader.indexOf(" ") + 1);
        if (!jwtService.isTokenValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        Claims claims = jwtService.extractClaims(token);
        String username = claims.getSubject();
        long id = claims.get("userId", Long.class);
        AuthUserDto authUserDto = new AuthUserDto(id, username);
        request.setAttribute("authUserDto", authUserDto);

        return true;
    }
}
