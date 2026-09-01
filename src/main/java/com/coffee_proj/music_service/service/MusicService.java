package com.coffee_proj.music_service.service;

import com.coffee_proj.music_service.entity.MusicEntity;
import com.coffee_proj.music_service.entity.UserEntity;
import com.coffee_proj.music_service.exception.MusicNotFoundException;
import com.coffee_proj.music_service.exception.UserNotFoundException;
import com.coffee_proj.music_service.model.Music;
import com.coffee_proj.music_service.repository.MusicRepo;
import com.coffee_proj.music_service.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MusicService {
    private final MusicRepo musicRepo;
    private final UserRepo userRepo;
    public MusicService(MusicRepo musicRepo, UserRepo userRepo) {
        this.musicRepo = musicRepo;
        this.userRepo = userRepo;
    }

    public Music createMusic(MusicEntity music, Long userid) throws UserNotFoundException {
        Optional<UserEntity> optUser = userRepo.findById(userid);
        if (optUser.isEmpty()) {
            throw new UserNotFoundException("пользователя с таким Id не существует");
        }
        UserEntity user = optUser.get();
        music.setUser(user);
        return Music.fromEntyityl(musicRepo.save(music));
    }

    public Long delete(Long id) throws MusicNotFoundException {
        Optional<MusicEntity> musicOpt = musicRepo.findById(id);
        if (musicOpt.isEmpty()) {
            throw new MusicNotFoundException("песни с таким Id не существует");
        }
        musicRepo.deleteById(id);
        return id;
    }
}
