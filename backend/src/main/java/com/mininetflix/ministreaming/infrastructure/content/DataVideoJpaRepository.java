package com.mininetflix.ministreaming.infrastructure.content;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataVideoJpaRepository
                extends JpaRepository<VideoContentEntity, String> {
}