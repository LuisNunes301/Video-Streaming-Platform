package com.mininetflix.ministreaming.application.content.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mininetflix.ministreaming.application.content.dto.VideoResponse;
import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;

@Service
public class ListVideosUseCaseImpl implements ListVideosUseCase {

    private final VideoCatalogRepository repository;

    public ListVideosUseCaseImpl(VideoCatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VideoResponse> execute() {

        return repository.findAll()
                .stream()
                .map(video -> new VideoResponse(
                        video.getId(),
                        video.getTitle(),
                        video.getStatus(),
                        video.getDuration(),
                        video.getThumbnailUrl()))
                .toList();
    }

}
