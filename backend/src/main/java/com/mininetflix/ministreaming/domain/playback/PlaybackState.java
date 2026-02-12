package com.mininetflix.ministreaming.domain.playback;

import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaybackState {

    private String userId;
    private String contentId;

    private double currentTime;
    private double duration;

    private boolean completed;

    private Instant lastUpdated;

    public PlaybackState(String userId, String contentId, double duration) {
        this.userId = userId;
        this.contentId = contentId;
        this.duration = duration;
        this.currentTime = 0.0;
        this.completed = false;
        this.lastUpdated = Instant.now();
    }

    public void updateProgress(double newTime, double duration) {

        if (newTime < 0) {
            throw new IllegalArgumentException("Progress cannot be negative");
        }

        this.duration = duration;

        this.currentTime = Math.min(newTime, duration);

        // Netflix-style: 95% = completo
        this.completed = duration > 0 &&
                this.currentTime >= (duration * 0.95);

        this.lastUpdated = Instant.now();
    }
}