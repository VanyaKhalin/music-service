package com.coffee_proj.music_service.controller;

import com.coffee_proj.music_service.entity.MusicEntity;
import com.coffee_proj.music_service.exception.UserNotFoundException;
import com.coffee_proj.music_service.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
public class MusicController {
    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping
    public ResponseEntity createMusic(@RequestBody MusicEntity music, @RequestParam Long userid) throws UserNotFoundException {
        return ResponseEntity.ok(musicService.createMusic(music, userid));
    }
}
