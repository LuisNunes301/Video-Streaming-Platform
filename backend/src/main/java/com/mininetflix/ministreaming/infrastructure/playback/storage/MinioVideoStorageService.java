package com.mininetflix.ministreaming.infrastructure.playback.storage;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mininetflix.ministreaming.application.content.port.VideoStorageService;

import java.util.concurrent.TimeUnit;

@Component
public class MinioVideoStorageService implements VideoStorageService {

    private final MinioClient minioClient;

    public MinioVideoStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String generatePresignedUrl(String bucket, String objectKey) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(objectKey)
                            .expiry(10, TimeUnit.MINUTES)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar URL do vídeo", e);
        }
    }

    public void upload(String bucket,
            String objectKey,
            MultipartFile file) {

        try {

            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(bucket)
                            .build());

            if (!exists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucket)
                                .build());
            }

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectKey)
                            .stream(file.getInputStream(),
                                    file.getSize(),
                                    -1)
                            .contentType(file.getContentType())
                            .build());

        } catch (Exception e) {
            throw new RuntimeException("Upload failed", e);
        }
    }
}
