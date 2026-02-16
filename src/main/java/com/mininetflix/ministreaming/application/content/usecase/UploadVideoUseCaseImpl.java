package com.mininetflix.ministreaming.application.content.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mininetflix.ministreaming.application.content.dto.UploadVideoInput;
import com.mininetflix.ministreaming.application.content.dto.UploadVideoOutput;
import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.application.content.port.VideoMetadataExtractor;
import com.mininetflix.ministreaming.application.content.port.VideoStorageService;
import com.mininetflix.ministreaming.domain.content.VideoContent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadVideoUseCaseImpl implements UploadVideoUseCase {

        private final VideoStorageService storageService;
        private final VideoCatalogRepository catalogRepository;
        private final VideoMetadataExtractor metadataExtractor;

        @Override
        public UploadVideoOutput execute(
                        UploadVideoInput input) {

                if (!"video/mp4".equals(input.file().getContentType())) {
                        throw new IllegalArgumentException("Only MP4 files are allowed");
                }

                String objectKey = UUID.randomUUID() + ".mp4";
                String id = UUID.randomUUID().toString();
                storageService.upload(input.bucket(), objectKey, input.file());

                double duration = metadataExtractor.extractDuration(input.file());

                VideoContent video = VideoContent.create(
                                id,
                                input.title(),
                                input.bucket(),
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