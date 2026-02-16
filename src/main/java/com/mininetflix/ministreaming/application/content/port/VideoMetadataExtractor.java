package com.mininetflix.ministreaming.application.content.port;

import org.springframework.web.multipart.MultipartFile;

public interface VideoMetadataExtractor {
    double extractDuration(MultipartFile file);
}
