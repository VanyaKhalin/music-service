package com.coffee_proj.music_service.model;

import com.coffee_proj.music_service.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"id", "username", "role", "music"})
public class User {
    private Long id;
    private String username;
    private String role;
    private List<Music> music;

    public User() {
    }

    public static User fromEntyity(UserEntity user) {
        User model = new User();
        model.setId(user.getId());
        model.setUsername(user.getUsername());
        model.setRole(user.getRole());
        model.setMusic(user.getUsersMusic().stream().map(Music::fromEntyityl).collect(Collectors.toList()));
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
