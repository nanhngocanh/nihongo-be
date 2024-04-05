package com.hedspi.nihongobe.payload.request;

public class StudyLessonDuration extends StudyDuration{
    private Integer lessonId;

    public StudyLessonDuration(Integer duration, Integer lessonId) {
        super(duration);
        this.lessonId = lessonId;
    }

    public StudyLessonDuration(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }
}
