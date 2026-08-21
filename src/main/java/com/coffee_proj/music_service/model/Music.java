package com.coffee_proj.music_service.model;

import com.coffee_proj.music_service.entity.MusicEntity;

public class Music {
    private Long id;
    private String songname;

    public Music() {
    }

    public static Music toModel(MusicEntity music) {
        Music model = new Music();
        model.setId(music.getId());
        model.setSongname(music.getSongname());
        return model;
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
}
