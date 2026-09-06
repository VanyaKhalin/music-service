package com.coffee_proj.music_service.repository;

import com.coffee_proj.music_service.entity.MusicEntity;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepo extends CrudRepository<MusicEntity, Long> {

}
