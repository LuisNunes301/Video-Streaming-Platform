package com.mininetflix.ministreaming.application.content.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.application.playback.port.VideoStorageService;
import com.mininetflix.ministreaming.domain.content.UploadVideoInput;
import com.mininetflix.ministreaming.domain.content.UploadVideoOutput;
import com.mininetflix.ministreaming.domain.content.VideoContent;

@Service
public class UploadVideoUseCaseImpl implements UploadVideoUseCase {

    private final VideoStorageService storageService;
    private final VideoCatalogRepository catalogRepository;

    public UploadVideoUseCaseImpl(
            VideoStorageService storageService,
            VideoCatalogRepository catalogRepository) {
        this.storageService = storageService;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public UploadVideoOutput execute(UploadVideoInput input) {

        MultipartFile file = input.file();

        if (!"video/mp4".equals(file.getContentType())) {
            throw new IllegalArgumentException("Only MP4 files are allowed");
        }

        String objectKey = UUID.randomUUID() + ".mp4";

        storageService.upload(
                input.bucket(),
                objectKey,
                file);

        VideoContent video = VideoContent.create(
                input.title(),
                input.bucket(),
                objectKey,
                input.duration());

        catalogRepository.save(video);

        return new UploadVideoOutput(
                video.getId(),
                video.getTitle(),
                video.getBucket(),
                video.getObjectKey());
    }
}
