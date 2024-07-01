package com.hedspi.nihongobe.payload.data;

public class FileModel {
    private String videoFileName ;
    private Double duration ;

    public FileModel(String videoFileName, Double duration) {
        this.videoFileName = videoFileName;
        this.duration = duration;
    }

    public FileModel() {
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}

