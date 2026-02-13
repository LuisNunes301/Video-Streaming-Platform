package com.mininetflix.ministreaming.infrastructure.content;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class VideoContentEntity {

    @Id
    private String id;

    private String title;

    private String bucket;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    private String objectKey;

    private double duration;

    private boolean active = true;

    public VideoContentEntity() {
    }

    public VideoContentEntity(String id, String title, String objectKey, double duration, String bucket,
            boolean active) {
        this.id = id;
        this.title = title;
        this.objectKey = objectKey;
        this.bucket = bucket;
        this.duration = duration;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
