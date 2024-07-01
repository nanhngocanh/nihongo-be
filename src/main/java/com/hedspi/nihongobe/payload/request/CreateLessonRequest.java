package com.hedspi.nihongobe.payload.request;

import com.hedspi.nihongobe.payload.data.Theory;

import java.util.List;

public class CreateLessonRequest {
    private Integer no = 0;
    private String title;
    private String description;
    private String sample;
    private Integer duration;
    private Boolean isActive = false;

    private List<Theory> theories;

    public CreateLessonRequest() {
    }

    public CreateLessonRequest(Integer no, String title, String description, String sample, String videoUrl, Integer duration, Boolean isActive, List<Theory> theories) {
        this.no = no;
        this.title = title;
        this.description = description;
        this.sample = sample;
        this.duration = duration;
        this.isActive = isActive;
        this.theories = theories;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Theory> getTheories() {
        return theories;
    }

    public void setTheories(List<Theory> theories) {
        this.theories = theories;
    }
}
