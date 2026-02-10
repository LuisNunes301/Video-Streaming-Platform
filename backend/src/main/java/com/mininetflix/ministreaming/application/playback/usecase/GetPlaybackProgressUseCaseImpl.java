package com.mininetflix.ministreaming.application.playback.usecase;

import java.util.Optional;

import com.mininetflix.ministreaming.application.playback.port.PlaybackRepository;
import com.mininetflix.ministreaming.domain.playback.PlaybackState;

public class GetPlaybackProgressUseCaseImpl
        implements GetPlaybackProgressUseCase {

    private final PlaybackRepository playbackRepository;

    public GetPlaybackProgressUseCaseImpl(
            PlaybackRepository playbackRepository) {
        this.playbackRepository = playbackRepository;
    }

    @Override
    public Optional<PlaybackState> execute(
            String userId,
            String contentId) {
        return playbackRepository.findByUserAndContent(userId, contentId);
    }
}
