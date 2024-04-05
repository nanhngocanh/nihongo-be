package com.hedspi.nihongobe.payload.response;

import java.time.LocalDate;

public class StudyTimePerDay {
    private Integer minutes;
    private LocalDate date;

    public StudyTimePerDay(Integer minutes, LocalDate date) {
        this.minutes = minutes;
        this.date = date;
    }

    public StudyTimePerDay() {
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
