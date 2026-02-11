package com.mininetflix.ministreaming.application.playback.usecase;

import org.springframework.stereotype.Service;

import com.mininetflix.ministreaming.application.content.port.VideoCatalogRepository;
import com.mininetflix.ministreaming.application.playback.port.PlaybackRepository;
import com.mininetflix.ministreaming.domain.playback.PlaybackState;
import com.mininetflix.ministreaming.domain.playback.exception.VideoNotFoundException;

@Service
public class SavePlaybackProgressUseCaseImpl
        implements SavePlaybackProgressUseCase {

    private final PlaybackRepository playbackRepository;
    private final VideoCatalogRepository videoCatalogRepository;

    public SavePlaybackProgressUseCaseImpl(
            PlaybackRepository playbackRepository,
            VideoCatalogRepository videoCatalogRepository) {
        this.playbackRepository = playbackRepository;
        this.videoCatalogRepository = videoCatalogRepository;
    }

    @Override
    public void execute(String userId, String contentId, double currentTime) {

        var content = videoCatalogRepository.findById(contentId)
                .orElseThrow(() -> new VideoNotFoundException(contentId));

        PlaybackState playback = playbackRepository
                .findByUserAndContent(userId, contentId)
                .orElse(new PlaybackState(
                        userId,
                        contentId,
                        content.getDuration()));

        playback.updateProgress(currentTime);

        playbackRepository.save(playback);
    }
}
