package com.mininetflix.ministreaming.application.playback.usecase;

import com.mininetflix.ministreaming.domain.playback.PlaybackState;

public interface SavePlaybackProgressUseCase {
    void execute(PlaybackState state);
}
