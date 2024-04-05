package com.hedspi.nihongobe.payload.data;

public class Sentence {
    private String sample;
    private String reply;

    public Sentence(String sample, String reply) {
        this.sample = sample;
        this.reply = reply;
    }

    public Sentence() {
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
