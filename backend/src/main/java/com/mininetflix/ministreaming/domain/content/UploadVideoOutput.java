package com.mininetflix.ministreaming.domain.content;

public record UploadVideoOutput(
        String id,
        String title,
        String bucket,
        String objectKey) {
}
