package com.mininetflix.ministreaming.infrastructure.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mininetflix.ministreaming.infrastructure.content.entity.VideoContentEntity;

public interface DataVideoJpaRepository
        extends JpaRepository<VideoContentEntity, String> {
}