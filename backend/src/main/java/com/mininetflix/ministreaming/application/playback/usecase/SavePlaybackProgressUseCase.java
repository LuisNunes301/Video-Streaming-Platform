package com.mininetflix.ministreaming.application.playback.usecase;

public interface SavePlaybackProgressUseCase {
    void execute(String userId, String contentId, double currentTime);;
}
