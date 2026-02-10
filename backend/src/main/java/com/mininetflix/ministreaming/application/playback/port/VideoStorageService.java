package com.mininetflix.ministreaming.application.playback.port;

public interface VideoStorageService {
    String generatePresignedUrl(String objectPath);

}