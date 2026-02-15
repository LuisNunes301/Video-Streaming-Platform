package com.mininetflix.ministreaming.application.playback.port;

import java.util.List;
import java.util.Optional;

import com.mininetflix.ministreaming.domain.playback.PlaybackState;

public interface PlaybackRepository {

    Optional<PlaybackState> findByUserAndContent(
            String userId,
            String contentId);

    void save(PlaybackState state);

    List<PlaybackState> findByUserAndNotCompleted(String userId);
}
