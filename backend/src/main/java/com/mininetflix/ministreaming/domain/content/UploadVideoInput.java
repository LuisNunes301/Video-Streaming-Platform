package com.mininetflix.ministreaming.domain.content;

import org.springframework.web.multipart.MultipartFile;

public record UploadVideoInput(
        String title,
        String bucket,
        double duration,
        MultipartFile file) {
}
