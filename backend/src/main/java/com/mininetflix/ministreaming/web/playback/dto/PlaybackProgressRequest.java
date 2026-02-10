package com.mininetflix.ministreaming.web.playback.dto;

public class PlaybackProgressRequest {

    private String contentId;
    private Double currentTime;

    public String getContentId() {
        return contentId;
    }

    public Double getCurrentTime() {
        return currentTime;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public void setCurrentTime(Double currentTime) {
        this.currentTime = currentTime;
    }
}