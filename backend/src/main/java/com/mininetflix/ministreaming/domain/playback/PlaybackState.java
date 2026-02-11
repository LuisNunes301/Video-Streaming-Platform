package com.mininetflix.ministreaming.domain.playback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PlaybackState {

    private final String userId;
    private final String contentId;
    private double currentTime;
    private final double duration;
    private boolean completed;

    public PlaybackState(String userId, String contentId, double duration) {
        this.userId = userId;
        this.contentId = contentId;
        this.duration = duration;
        this.currentTime = 0;
        this.completed = false;
    }

    public void updateProgress(double newTime) {
        if (newTime < 0) {
            throw new IllegalArgumentException("Progress cannot be negative");
        }

        this.currentTime = Math.min(newTime, duration);

        if (this.currentTime >= duration) {
            this.completed = true;
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getUserId() {
        return userId;
    }

    public String getContentId() {
        return contentId;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public double getDuration() {
        return duration;
    }
}
