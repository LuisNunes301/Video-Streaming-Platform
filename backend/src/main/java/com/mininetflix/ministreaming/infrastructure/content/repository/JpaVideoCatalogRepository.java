package com.mininetflix.ministreaming.infrastructure.content.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.domain.content.VideoContent;
import com.mininetflix.ministreaming.infrastructure.content.entity.VideoContentEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaVideoCatalogRepository
                implements VideoCatalogRepository {

        private final DataVideoJpaRepository jpaRepository;

        @Override
        public void save(VideoContent video) {

                VideoContentEntity entity = new VideoContentEntity(
                                video.getId(),
                                video.getTitle(),
                                video.getBucket(),
                                video.getObjectKey(),
                                video.getDuration(),
                                video.isActive());

                jpaRepository.save(entity);
        }

        @Override
        public Optional<VideoContent> findById(String id) {
                return jpaRepository.findById(id)
                                .map(entity -> VideoContent.create(
                                                entity.getTitle(),
                                                entity.getBucket(),
                                                entity.getObjectKey(),
                                                entity.getDuration()));
        }

        @Override
        public List<VideoContent> findAll() {
                return jpaRepository.findAll()
                                .stream()
                                .map(entity -> VideoContent.create(
                                                entity.getTitle(),
                                                entity.getBucket(),
                                                entity.getObjectKey(),
                                                entity.getDuration()))
                                .toList();
        }
}