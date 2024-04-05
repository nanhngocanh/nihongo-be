package com.hedspi.nihongobe.payload.response;

import com.hedspi.nihongobe.payload.data.Answer;

import java.util.List;

public class TestQuestion {
    private String question;
    private List<Answer> answers;

    public TestQuestion(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public TestQuestion() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
