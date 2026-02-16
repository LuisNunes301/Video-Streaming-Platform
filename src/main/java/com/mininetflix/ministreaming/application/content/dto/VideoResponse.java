package com.mininetflix.ministreaming.application.content.dto;

import com.mininetflix.ministreaming.domain.content.VideoStatus;

public record VideoResponse(
        String id,
        String title,
        VideoStatus status,
        Double duration,
        String thumbnailUrl) {
}
