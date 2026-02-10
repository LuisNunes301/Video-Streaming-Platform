package com.mininetflix.ministreaming.application.playback.port;

import java.util.Optional;

import com.mininetflix.ministreaming.domain.playback.PlaybackState;

public interface PlaybackProgressRepository {

    void save(String userId, String contentId, PlaybackState state);

    Optional<PlaybackState> find(String userId, String contentId);
}
