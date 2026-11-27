package com.coffeeproj.music_service.entity;

import jakarta.persistence.*;

import java.util.UUID;

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
    private String objectKey;

    public MusicEntity(){}

    public MusicEntity(String songname, UserEntity user) {
        this.songname = songname;
        this.user = user;
        this.objectKey = "songs/" + UUID.randomUUID();
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
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
