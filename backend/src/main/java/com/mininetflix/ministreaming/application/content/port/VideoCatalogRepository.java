package com.mininetflix.ministreaming.application.content.port;

import java.util.List;
import java.util.Optional;

import com.mininetflix.ministreaming.domain.content.VideoContent;

public interface VideoCatalogRepository {
    Optional<VideoContent> findById(String id);

    List<VideoContent> findAll();
}
