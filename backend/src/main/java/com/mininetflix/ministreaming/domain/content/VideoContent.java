package com.mininetflix.ministreaming.domain.content;

public class VideoContent {

    private final String id;
    private final String title;
    private final String bucket;
    private final String objectKey;
    private final double duration;
    private final boolean active;

    public VideoContent(
            String id,
            String title,
            String bucket,
            String objectKey,
            double duration,
            boolean active) {
        this.id = id;
        this.title = title;
        this.bucket = bucket;
        this.objectKey = objectKey;
        this.duration = duration;
        this.active = active;
    }

    public static VideoContent create(
            String id,
            String title,
            String bucket,
            String objectKey,
            double duration) {
        return new VideoContent(
                id,
                title,
                bucket,
                objectKey,
                duration,
                true);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBucket() {
        return bucket;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public double getDuration() {
        return duration;
    }

    public boolean isActive() {
        return active;
    }
}