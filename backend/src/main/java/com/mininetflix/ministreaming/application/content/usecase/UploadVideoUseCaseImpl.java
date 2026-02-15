package com.mininetflix.ministreaming.application.content.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mininetflix.ministreaming.application.content.dto.UploadVideoOutput;
import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.application.content.port.VideoStorageService;
import com.mininetflix.ministreaming.domain.content.VideoContent;
import com.mininetflix.ministreaming.web.controller.playback.dto.UploadVideoRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadVideoUseCaseImpl implements UploadVideoUseCase {

        private final VideoStorageService storageService;
        private final VideoCatalogRepository catalogRepository;

        @Override
        public UploadVideoOutput execute(
                        String title,
                        String bucket,
                        MultipartFile file) {

                if (!"video/mp4".equals(file.getContentType())) {
                        throw new IllegalArgumentException("Only MP4 files are allowed");
                }

                String objectKey = UUID.randomUUID() + ".mp4";

                storageService.upload(bucket, objectKey, file);

                // 🔥 por enquanto fixo (depois vamos automatizar metadata)
                double duration = 120.0;

                VideoContent video = VideoContent.create(
                                title,
                                bucket,
                                objectKey,
                                duration);

                catalogRepository.save(video);

                return new UploadVideoOutput(
                                video.getId(),
                                video.getTitle(),
                                video.getBucket(),
                                video.getObjectKey());
        }
}