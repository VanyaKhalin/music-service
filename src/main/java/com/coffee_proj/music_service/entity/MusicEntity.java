package com.coffee_proj.music_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class MusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String songname;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public MusicEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
