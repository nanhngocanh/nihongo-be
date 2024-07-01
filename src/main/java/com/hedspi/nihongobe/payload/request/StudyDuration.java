package com.hedspi.nihongobe.payload.request;

public class StudyDuration {
    private Integer duration;//second

    public StudyDuration(Integer duration) {
        this.duration = duration;
    }

    public StudyDuration() {
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
