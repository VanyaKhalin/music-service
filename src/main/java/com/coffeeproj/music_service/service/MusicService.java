package com.coffeeproj.music_service.service;

import com.coffeeproj.music_service.entity.MusicEntity;
import com.coffeeproj.music_service.entity.UserEntity;
import com.coffeeproj.music_service.exception.MusicNotFoundException;
import com.coffeeproj.music_service.exception.UserNotFoundException;
import com.coffeeproj.music_service.model.Music;
import com.coffeeproj.music_service.repository.MusicRepo;
import com.coffeeproj.music_service.repository.UserRepo;
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

    public long delete(long id, long userId) throws MusicNotFoundException {
        UserEntity user = userRepo.findById(userId).get();
        Optional<MusicEntity> musicOpt = musicRepo.findById(id);
        if (musicOpt.isEmpty()) {
            throw new MusicNotFoundException("Песни с таким Id не существует");
        }
        MusicEntity music = musicOpt.get();
        if (user.getId() == music.getUser().getId()) {
            musicRepo.deleteById(id);
            return id;
        } else {
            throw new MusicNotFoundException("Песни с таким Id не существует");
        }
    }
}
