package com.hedspi.nihongobe.payload.response;

import com.hedspi.nihongobe.enums.Status;

public class LessonResponse {
    private Integer id;

    private Integer no;
    private String title;

    private Status status;

    public LessonResponse(Integer id, Integer no, String title, Status status) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.status = status;
    }

    public LessonResponse() {
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
