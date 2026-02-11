package com.mininetflix.ministreaming.domain.content;

public class VideoContent {

    private final String id;
    private final String title;
    private final String objectPath;
    private final double duration;

    public VideoContent(String id, String title, String objectPath, double duration) {
        this.id = id;
        this.title = title;
        this.objectPath = objectPath;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getObjectPath() {
        return objectPath;
    }

    public double getDuration() {
        return duration;
    }
}
