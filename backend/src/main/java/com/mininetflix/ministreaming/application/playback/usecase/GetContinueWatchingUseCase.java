package com.mininetflix.ministreaming.application.playback.usecase;

import java.util.List;

import com.mininetflix.ministreaming.domain.playback.PlaybackState;

public interface GetContinueWatchingUseCase {

    List<PlaybackState> execute(String userId);
}