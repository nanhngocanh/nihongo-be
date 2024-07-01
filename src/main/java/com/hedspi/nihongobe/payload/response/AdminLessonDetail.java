package com.hedspi.nihongobe.payload.response;

import com.hedspi.nihongobe.payload.data.Theory;
import com.hedspi.nihongobe.payload.request.CreateLessonRequest;


import java.time.Instant;
import java.util.List;

public class AdminLessonDetail extends CreateLessonRequest {

    private Instant createAt;
    private Instant updateAt;
    public AdminLessonDetail() {
    }

    public AdminLessonDetail(Instant createAt, Instant updateAt) {
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public AdminLessonDetail(Integer no, String title, String description, String sample, String videoUrl, Integer duration, Boolean isActive, List<Theory> theories, Instant createAt, Instant updateAt) {
        super(no, title, description, sample, videoUrl, duration, isActive, theories);
        this.createAt = createAt;
        this.updateAt = updateAt;
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
}
