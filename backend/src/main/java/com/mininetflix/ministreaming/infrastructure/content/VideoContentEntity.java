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

    private String objectPath;

    private double duration;

    public VideoContentEntity() {
    }

    public VideoContentEntity(String id, String title, String objectPath, double duration) {
        this.id = id;
        this.title = title;
        this.objectPath = objectPath;
        this.duration = duration;
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

    public String getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(String objectPath) {
        this.objectPath = objectPath;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

}
