package com.coffeeproj.music_service.controller;

import com.coffeeproj.music_service.controller.dto.AuthUserDto;
import com.coffeeproj.music_service.entity.MusicEntity;
import com.coffeeproj.music_service.exception.MusicNotFoundException;
import com.coffeeproj.music_service.exception.UserNotFoundException;
import com.coffeeproj.music_service.service.MusicService;
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
    public ResponseEntity createMusic(@RequestBody MusicEntity music, @RequestAttribute("authUserDto")AuthUserDto authUserDto) throws UserNotFoundException {
        return ResponseEntity.status(201).body(musicService.createMusic(music, authUserDto.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMusic(@PathVariable long id, @RequestAttribute("authUserDto") AuthUserDto authUserDto) throws MusicNotFoundException {
        return ResponseEntity.ok(musicService.delete(id, authUserDto.getId()));
    }
}
