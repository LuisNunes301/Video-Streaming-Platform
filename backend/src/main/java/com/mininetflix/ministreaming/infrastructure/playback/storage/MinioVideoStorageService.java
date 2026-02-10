package com.mininetflix.ministreaming.infrastructure.storage;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import org.springframework.stereotype.Component;

import com.mininetflix.ministreaming.application.playback.port.VideoStorageService;

import java.util.concurrent.TimeUnit;

@Component
public class MinioVideoStorageService implements VideoStorageService {

    private final MinioClient minioClient;

    public MinioVideoStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String generatePresignedUrl(String objectPath) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket("videos")
                            .object(objectPath)
                            .expiry(10, TimeUnit.MINUTES)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar URL do vídeo", e);
        }
    }
}
