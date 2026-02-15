package com.mininetflix.ministreaming.application.content.usecase;

import org.springframework.web.multipart.MultipartFile;

import com.mininetflix.ministreaming.application.content.dto.UploadVideoOutput;

public interface UploadVideoUseCase {
    UploadVideoOutput execute(
            String title,
            String bucket,
            MultipartFile file);
}