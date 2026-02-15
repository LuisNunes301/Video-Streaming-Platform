package com.mininetflix.ministreaming.infrastructure.content;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.domain.content.VideoContent;

@Component
public class VideojpaCatalogRepository
                implements VideoCatalogRepository {

        private final DataVideoJpaRepository jpaRepository;

        public VideojpaCatalogRepository(
                        DataVideoJpaRepository jpaRepository) {
                this.jpaRepository = jpaRepository;
        }

        @Override
        public Optional<VideoContent> findById(String id) {
                return jpaRepository.findById(id)
                                .map(entity -> new VideoContent(
                                                entity.getId(),
                                                entity.getTitle(),
                                                entity.getBucket(),
                                                entity.getObjectKey(),
                                                entity.getDuration(),
                                                entity.isActive()));
        }

        @Override
        public List<VideoContent> findAll() {
                return jpaRepository.findAll()
                                .stream()
                                .map(entity -> new VideoContent(
                                                entity.getId(),
                                                entity.getTitle(),
                                                entity.getBucket(),
                                                entity.getObjectKey(),
                                                entity.getDuration(),
                                                entity.isActive()))
                                .toList();
        }

        @Override
        public VideoContent save(VideoContent video) {

                VideoContentEntity entity = new VideoContentEntity(
                                video.getId(),
                                video.getTitle(),
                                video.getObjectKey(),
                                video.getDuration(),
                                video.getBucket(),
                                video.isActive());

                VideoContentEntity saved = jpaRepository.save(entity);

                return new VideoContent(
                                saved.getId(),
                                saved.getTitle(),
                                saved.getBucket(),
                                saved.getObjectKey(),
                                saved.getDuration(),
                                saved.isActive());
        }
}
