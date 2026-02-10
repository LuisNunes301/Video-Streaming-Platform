package com.mininetflix.ministreaming;

import org.springframework.stereotype.Service;

import io.minio.MinioClient;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import java.util.concurrent.TimeUnit;

@Service
public class VideoStorageService {
    private final MinioClient minioClient;

    public VideoStorageService(
            MinioClient minioClient) {
        this.minioClient = minioClient;

    }

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
