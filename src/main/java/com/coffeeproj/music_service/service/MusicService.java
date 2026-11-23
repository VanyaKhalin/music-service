package com.coffeeproj.music_service.service;

import com.coffeeproj.music_service.controller.dto.MusicDto;
import com.coffeeproj.music_service.entity.MusicEntity;
import com.coffeeproj.music_service.entity.UserEntity;
import com.coffeeproj.music_service.exception.MusicNotFoundException;
import com.coffeeproj.music_service.exception.UserNotFoundException;
import com.coffeeproj.music_service.model.Music;
import com.coffeeproj.music_service.repository.MusicRepo;
import com.coffeeproj.music_service.repository.UserRepo;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.Http;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class MusicService {
    private final MusicRepo musicRepo;
    private final UserRepo userRepo;
    private final MinioClient minioClient;
    private String bucketName;


    public MusicService(MusicRepo musicRepo, UserRepo userRepo, MinioClient minioClient,  @Value("${minio.bucket}") String bucketName) {
        this.musicRepo = musicRepo;
        this.userRepo = userRepo;
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }

    public String createMusic(MusicDto musicDto, Long userId) throws UserNotFoundException, MinioException {
        UserEntity user = userRepo.findById(userId).get();
        MusicEntity music = new MusicEntity(musicDto.getName(), user);
        String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Http.Method.PUT)
                .bucket(bucketName)
                .object(music.getObjectKey())
                .expiry(1, TimeUnit.DAYS)
                .build());
        musicRepo.save(music);
        return url;
    }

    public String getMusic(long id, long userId) throws UserNotFoundException, MinioException, MusicNotFoundException {
        Optional<MusicEntity> musicOpt = musicRepo.findById(id);
        if (musicOpt.isEmpty()) {
            throw new MusicNotFoundException("Песни с таким Id не существует");
        }
        MusicEntity music = musicOpt.get();

        if (!music.getUser().getId().equals(userId)) {
            throw new MusicNotFoundException("Песни с таким Id не найдено");
        }

        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Http.Method.GET)
                .bucket(bucketName)
                .object(music.getObjectKey())
                .expiry(1, TimeUnit.DAYS)
                .build());
    }

    public String deleteMusic(long id, long userId) throws MusicNotFoundException, MinioException {
        Optional<MusicEntity> musicOpt = musicRepo.findById(id);
        if (musicOpt.isEmpty()) {
            throw new MusicNotFoundException("Песни с таким Id не существует");
        }
        MusicEntity music = musicOpt.get();

        if (!music.getUser().getId().equals(userId)) {
            throw new MusicNotFoundException("Песни с таким Id не найдеа");
        }

        musicRepo.deleteById(id);
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                        .method(Http.Method.DELETE)
                        .bucket(bucketName)
                        .object(music.getObjectKey())
                        .expiry(1, TimeUnit.DAYS)
                        .build());
    }

    public String replaceMusic(long id, long userId) throws MusicNotFoundException, MinioException {
        Optional<MusicEntity> musicOpt = musicRepo.findById(id);
        if (musicOpt.isEmpty()) {
            throw new MusicNotFoundException("Песни с таким Id не существует");
        }

        MusicEntity music = musicOpt.get();
        if (!music.getUser().getId().equals(userId)) {
            throw new MusicNotFoundException("Песни с таким Id не найдеа");
        }

        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                        .method(Http.Method.PUT)
                        .bucket(bucketName)
                        .object(music.getObjectKey())
                        .expiry(1, TimeUnit.DAYS)
                        .build());
    }

}
