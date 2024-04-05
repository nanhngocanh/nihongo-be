package com.hedspi.nihongobe.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "study_time")
@EntityListeners(AuditingEntityListener.class)
public class StudyTime {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private String userId;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "date")
    private LocalDate date;

    public StudyTime() {
    }

    public StudyTime(Integer id, String userId, Integer duration, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.duration = duration;
        this.date = date;
    }

    public StudyTime(String userId, Integer duration, LocalDate date) {
        this.userId = userId;
        this.duration = duration;
        this.date = date;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
