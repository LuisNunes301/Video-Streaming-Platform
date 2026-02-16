package com.mininetflix.ministreaming.application.content.dto;

import com.mininetflix.ministreaming.domain.content.VideoStatus;

public record UploadVideoOutput(
                String id,
                String title,
                String bucket,
                String objectKey,
                VideoStatus status) {
}
