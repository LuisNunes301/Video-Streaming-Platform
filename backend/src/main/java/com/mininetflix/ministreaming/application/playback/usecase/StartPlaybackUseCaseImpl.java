package com.mininetflix.ministreaming.application.playback.usecase;

import com.mininetflix.ministreaming.application.dto.StartPlaybackOutput;
import com.mininetflix.ministreaming.application.playback.port.PlaybackRepository;
import com.mininetflix.ministreaming.application.playback.port.VideoStorageService;

public class StartPlaybackUseCaseImpl implements StartPlaybackUseCase {

    private final PlaybackRepository playbackRepository;
    private final VideoStorageService videoStorageService;

    public StartPlaybackUseCaseImpl(
            PlaybackRepository playbackRepository,
            VideoStorageService videoStorageService) {
        this.playbackRepository = playbackRepository;
        this.videoStorageService = videoStorageService;
    }

    @Override
    public StartPlaybackOutput execute(String userId, String contentId) {

        String videoPath = "videoplayback.mp4"; // fake por enquanto

        String videoUrl = videoStorageService.generatePresignedUrl(videoPath);

        double startAt = playbackRepository
                .findByUserAndContent(userId, contentId)
                .map(p -> p.getCurrentTime())
                .orElse(0.0);

        return new StartPlaybackOutput(videoUrl, startAt);
    }
}
