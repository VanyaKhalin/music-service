package com.coffeeproj.music_service.controller;

import com.coffeeproj.music_service.controller.dto.AuthUserDto;
import com.coffeeproj.music_service.controller.dto.MusicDto;
import com.coffeeproj.music_service.exception.MusicNotFoundException;
import com.coffeeproj.music_service.exception.UserNotFoundException;
import com.coffeeproj.music_service.service.MusicService;
import io.minio.errors.MinioException;
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
    public ResponseEntity createMusic(@RequestBody MusicDto musicDto,
                                      @RequestAttribute("authUserDto")AuthUserDto authUserDto)
                                        throws UserNotFoundException, MinioException {
        return ResponseEntity.status(201).body(musicService.createMusic(musicDto, authUserDto.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getMusic(@PathVariable long id, @RequestAttribute("authUserDto")AuthUserDto authUserDto)
            throws UserNotFoundException, MusicNotFoundException, MinioException {
        return ResponseEntity.status(200).body(musicService.getMusic(id, authUserDto.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMusic(@PathVariable long id, @RequestAttribute("authUserDto") AuthUserDto authUserDto)
            throws MusicNotFoundException, MinioException {
        return ResponseEntity.status(200).body(musicService.deleteMusic(id, authUserDto.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity replaceMusic(@PathVariable long id, @RequestAttribute("authUserDto") AuthUserDto authUserDto)
            throws MusicNotFoundException, MinioException {
        return ResponseEntity.status(200).body(musicService.replaceMusic(id, authUserDto.getId()));
    }


}
