package com.mininetflix.ministreaming.application.playback.usecase;

import java.util.Optional;

import com.mininetflix.ministreaming.domain.playback.PlaybackState;

public interface GetPlaybackProgressUseCase {
    Optional<PlaybackState> execute(String userId, String contentId);
}
