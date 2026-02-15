package com.mininetflix.ministreaming.application.content.dto;

public record UploadVideoOutput(
                String id,
                String title,
                String bucket,
                String objectKey) {
}
