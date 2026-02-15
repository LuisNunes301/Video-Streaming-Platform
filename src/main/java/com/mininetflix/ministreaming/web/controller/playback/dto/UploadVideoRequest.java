package com.mininetflix.ministreaming.web.controller.playback.dto;

public class UploadVideoRequest {
    private String title;

    private String bucket;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
