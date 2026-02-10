package com.mininetflix.ministreaming.application.playback.usecase;

import org.springframework.stereotype.Service;

import com.mininetflix.ministreaming.application.playback.port.PlaybackRepository;
import com.mininetflix.ministreaming.domain.playback.PlaybackState;

@Service
public class SavePlaybackProgressUseCaseImpl
        implements SavePlaybackProgressUseCase {

    private final PlaybackRepository playbackRepository;

    public SavePlaybackProgressUseCaseImpl(
            PlaybackRepository playbackRepository) {
        this.playbackRepository = playbackRepository;
    }

    @Override
    public void execute(PlaybackState state) {
        playbackRepository.save(state);
    }
}
