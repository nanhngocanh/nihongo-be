package com.hedspi.nihongobe.payload.response;

import com.hedspi.nihongobe.payload.data.Vocabulary;

public class LessonFlashcard {

    private String term;
    private String definition;

    public LessonFlashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public LessonFlashcard() {
    }

}
