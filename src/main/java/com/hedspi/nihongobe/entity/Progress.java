package com.hedspi.nihongobe.entity;

import com.hedspi.nihongobe.enums.Status;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "progress")
@EntityListeners(AuditingEntityListener.class)
public class Progress {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne()
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Progress(Integer id, String userId, Lesson lesson, Integer duration, Status status) {
        this.id = id;
        this.userId = userId;
        this.lesson = lesson;
        this.duration = duration;
        this.status = status;
    }

    public Progress(String userId, Lesson lesson, Integer duration, Status status) {

        this.userId = userId;
        this.lesson = lesson;
        this.duration = duration;
        this.status = status;
    }
    public Progress() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
