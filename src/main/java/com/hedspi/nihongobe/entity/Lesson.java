package com.hedspi.nihongobe.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;


@Entity
@Table(name = "lesson")
@EntityListeners(AuditingEntityListener.class)
public class Lesson {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no")
    private Integer no;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "sample")
    private String sample;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @Column(name = "is_active")
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(final String sample) {
        this.sample = sample;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Lesson(Integer id, Integer no, String title, String description, String sample, String videoUrl, Integer duration, Instant createAt, Instant updateAt, Boolean isActive) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.description = description;
        this.sample = sample;
        this.videoUrl = videoUrl;
        this.duration = duration;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.isActive = isActive;
    }

    public Lesson() {
    }
}