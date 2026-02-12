package com.mininetflix.ministreaming.application.content.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.domain.content.VideoContent;

@Service
public class ListVideosUseCaseImpl implements ListVideosUseCase {

    private final VideoCatalogRepository repository;

    public ListVideosUseCaseImpl(VideoCatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VideoContent> execute() {
        return repository.findAll();
    }
}
