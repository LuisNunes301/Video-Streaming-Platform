package com.mininetflix.ministreaming.application.content.port;

import org.springframework.web.multipart.MultipartFile;

public interface VideoStorageService {
    String generatePresignedUrl(String bucket, String objectKey);

    void upload(String bucket, String objectKey, MultipartFile file);
}